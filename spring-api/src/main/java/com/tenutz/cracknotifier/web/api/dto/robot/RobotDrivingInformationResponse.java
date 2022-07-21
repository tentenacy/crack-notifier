package com.tenutz.cracknotifier.web.api.dto.robot;

import com.tenutz.cracknotifier.domain.robot.RobotDrivingInformation;
import com.tenutz.cracknotifier.util.TimeUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RobotDrivingInformationResponse {

    private String seq;

    private Float latitude;

    private Float longitude;

    private Float x;

    private Float y;

    private String addressName;

    private String roadAddressName;

    private String region1DepthName;

    private String region2DepthName;

    private String region3DepthName;

    private String zoneNo;

    private Float velocity;

    private Float propellerVelocity;

    private Float slopeX;

    private Float slopeY;

    private Float slopeZ;

    private Float batteryVoltage;

    private String lastModifiedAt;

    public static RobotDrivingInformationResponse of(RobotDrivingInformation robotDrivingInformation) {
        return new RobotDrivingInformationResponse(
                robotDrivingInformation.getSeq(),
                robotDrivingInformation.getLatitude(),
                robotDrivingInformation.getLongitude(),
                robotDrivingInformation.getX(),
                robotDrivingInformation.getY(),
                robotDrivingInformation.getAddressName(),
                robotDrivingInformation.getRoadAddressName(),
                robotDrivingInformation.getRegion1DepthName(),
                robotDrivingInformation.getRegion2DepthName(),
                robotDrivingInformation.getRegion3DepthName(),
                robotDrivingInformation.getZoneNo(),
                robotDrivingInformation.getVelocity(),
                robotDrivingInformation.getPropellerVelocity(),
                robotDrivingInformation.getSlopeX(),
                robotDrivingInformation.getSlopeY(),
                robotDrivingInformation.getSlopeZ(),
                robotDrivingInformation.getBatteryVoltage(),
                TimeUtils.dateTimeFormatOf(robotDrivingInformation.getLastModifiedAt())
        );
    }
}
