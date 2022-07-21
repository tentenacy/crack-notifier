package com.tenutz.cracknotifier.web.api.dto.robot;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrivingInformationUpdateRequest {

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

    @NotNull
    private Float velocity;

    @NotNull
    private Float propellerVelocity;

    @NotNull
    private Float slopeX;

    @NotNull
    private Float slopeY;

    @NotNull
    private Float slopeZ;

    @NotNull
    private Float batteryVoltage;
}
