package com.tenutz.cracknotifier.util;

import org.springframework.web.util.UriComponentsBuilder;

public class FileUtils {
    public static String extractFileNameFrom(String url) {
        return UriComponentsBuilder.fromUriString(url).build().getPath().substring(1);
    }
}
