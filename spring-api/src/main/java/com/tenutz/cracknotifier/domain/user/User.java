package com.tenutz.cracknotifier.domain.user;

import com.tenutz.cracknotifier.domain.base.BaseTimeEntity;
import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.util.manager.SeqManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter @Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User extends BaseTimeEntity implements UserDetails {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_user")
    @GenericGenerator(name = "seq_manager_user", strategy = "com.tenutz.cracknotifier.util.manager.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "user_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String seq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "robot_seq")
    private Robot robot;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String username;

    @ElementCollection(fetch = EAGER)
    private List<String> roles = new ArrayList<>();

    private String provider;

    private String fcmToken;

    public static User create(String email, String password, String username) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setRoles(Collections.singletonList("ROLE_USER"));
        return user;
    }

    public static User create(String email, String password, String username, String provider) {
        User user = create(email, password, username);
        user.setProvider(provider);
        return user;
    }

    public void update(String username) {
        setUsername(username);
    }

    public void registerRobot(Robot robot) {
        setRobot(robot);
    }

    public void deregisterRobot() {
        setRobot(null);
    }

    public void updateFcmToken(String fcmToken) {
        setFcmToken(fcmToken);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
