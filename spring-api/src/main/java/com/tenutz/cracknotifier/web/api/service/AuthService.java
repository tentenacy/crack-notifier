package com.tenutz.cracknotifier.web.api.service;

import com.tenutz.cracknotifier.config.security.JwtProvider;
import com.tenutz.cracknotifier.domain.refreshtoken.RefreshToken;
import com.tenutz.cracknotifier.domain.refreshtoken.RefreshTokenRepository;
import com.tenutz.cracknotifier.domain.user.User;
import com.tenutz.cracknotifier.domain.user.UserRepository;
import com.tenutz.cracknotifier.util.EntityUtils;
import com.tenutz.cracknotifier.web.api.dto.common.TokenRequest;
import com.tenutz.cracknotifier.web.api.dto.common.TokenResponse;
import com.tenutz.cracknotifier.web.api.dto.user.LoginRequest;
import com.tenutz.cracknotifier.web.api.dto.user.SignupRequest;
import com.tenutz.cracknotifier.web.api.exception.business.CInvalidValueException;
import com.tenutz.cracknotifier.web.api.exception.security.CTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void signup(SignupRequest req) {
        User user = User.create(req.getEmail(), req.getPassword(), req.getUsername(), req.getProvider());
        userRepository.findByEmail(user.getEmail()).ifPresent((foundUser) -> {
            throw new CInvalidValueException.CAlreadySignedupException();
        });
        userRepository.save(user);
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(CInvalidValueException.CEmailLoginFailedException::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CInvalidValueException.CEmailLoginFailedException();
        }
        refreshTokenRepository.findByKey(user.getSeq()).ifPresent(refreshTokenRepository::delete);
        TokenResponse tokenResponse = jwtProvider.createToken(user.getSeq(), user.getRoles());
        refreshTokenRepository.save(RefreshToken.create(user.getSeq(), tokenResponse.getRefreshToken()));
        return tokenResponse;
    }

    /**
     * TokenRequest??? ?????? ????????? ?????? ????????? ??????
     * * ???????????? ?????? ?????? ?????? -> ?????? ??? ???????????? ??????
     */
    @Transactional
    public TokenResponse reissue(TokenRequest request) {

        //???????????? ?????? ??????
        if(!jwtProvider.validationToken(request.getRefreshToken())) {
            throw new CTokenException.CRefreshTokenException();
        }

        String accessToken = request.getAccessToken();
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        User foundUser = EntityUtils.userThrowable(userRepository, ((User)authentication.getPrincipal()).getSeq());

        //???????????? ?????? ??????
        RefreshToken refreshToken = refreshTokenRepository.findByKey(foundUser.getSeq())
                .orElseThrow(CTokenException.CRefreshTokenException::new);

        //???????????? ?????? ?????????
        if(!refreshToken.getToken().equals(request.getRefreshToken())) {
            throw new CTokenException.CRefreshTokenException();
        }

        TokenResponse newCreatedToken = jwtProvider.createToken(foundUser.getSeq(), foundUser.getRoles());
        refreshToken.update(newCreatedToken.getRefreshToken());

        return newCreatedToken;
    }
}
