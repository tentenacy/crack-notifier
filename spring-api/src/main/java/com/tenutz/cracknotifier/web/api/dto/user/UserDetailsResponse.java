package com.tenutz.cracknotifier.web.api.dto.user;

import com.tenutz.cracknotifier.domain.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailsResponse {
    private String seq;
    private String email;
    private String username;
    private String provider;

    public UserDetailsResponse(User user) {
        this.seq = user.getSeq();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.provider = user.getProvider();
    }
}
