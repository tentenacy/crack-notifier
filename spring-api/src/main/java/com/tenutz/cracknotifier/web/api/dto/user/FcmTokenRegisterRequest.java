package com.tenutz.cracknotifier.web.api.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmTokenRegisterRequest {

    private String fcmToken;

}
