package com.tenutz.cracknotifier.web.api.dto.fcm;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmRequest {

    private List<String> registration_ids;
    private String priority;
    private Data data;

    @lombok.Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Data {
        private String topic;
        private String title;
        private String body;
    }
}
