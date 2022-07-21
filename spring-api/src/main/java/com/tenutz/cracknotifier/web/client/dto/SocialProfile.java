package com.tenutz.cracknotifier.web.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialProfile {

    private String email;
    private String username;
    private String profileImageUrl;
}
