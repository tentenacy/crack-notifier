package com.tenutz.cracknotifier.web.api.dto.crack;

import com.tenutz.cracknotifier.domain.common.Address;
import com.tenutz.cracknotifier.domain.crack.Crack;
import com.tenutz.cracknotifier.util.TimeUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CrackDetailsResponse {

    private String seq;
    private String robotSeq;
    private float accuracy;
    private Address address;
    private String imageUrl;
    private float latitude;
    private float longitude;
    private float x;
    private float y;
    private String createdAt;
    private String lastModifiedAt;

    public CrackDetailsResponse(Crack crack) {
        this.seq = crack.getSeq();
        this.robotSeq = crack.getRobot().getSeq();
        this.accuracy = crack.getAccuracy();
        this.address = crack.getAddress();
        this.imageUrl = crack.getImage();
        this.latitude = crack.getLatitude();
        this.longitude = crack.getLongitude();
        this.x = crack.getX();
        this.y = crack.getY();
        this.createdAt = TimeUtils.dateTimeFormatOf(crack.getCreatedAt());
        this.lastModifiedAt = TimeUtils.dateTimeFormatOf(crack.getLastModifiedAt());
    }
}
