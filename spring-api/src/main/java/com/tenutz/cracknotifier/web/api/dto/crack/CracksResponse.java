package com.tenutz.cracknotifier.web.api.dto.crack;

import com.tenutz.cracknotifier.domain.crack.Crack;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CracksResponse {

    private String seq;
    private String imageUrl;
    private Date createdAt;
    private float accuracy;
    private String region3DepthName;

    public CracksResponse(Crack crack) {
        this.seq = crack.getSeq();
        this.imageUrl = crack.getImage();
        this.createdAt = crack.getCreatedAt();
        this.accuracy = crack.getAccuracy();
        this.region3DepthName = crack.getAddress().getRegion3DepthName();
    }
}
