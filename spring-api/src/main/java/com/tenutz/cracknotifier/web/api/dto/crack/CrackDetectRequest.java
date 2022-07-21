package com.tenutz.cracknotifier.web.api.dto.crack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CrackDetectRequest {

    @NotNull
    private MultipartFile image;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    private Float x;

    @NotNull
    private Float y;

    @NotEmpty
    private String addressName;

    private String roadAddressName;

    @NotEmpty
    private String region1DepthName;

    @NotEmpty
    private String region2DepthName;

    @NotEmpty
    private String region3DepthName;

    @NotEmpty
    private String zoneNo;
}
