package com.tenutz.cracknotifier.web.api.dto.crack;

import com.tenutz.cracknotifier.domain.common.Address;
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
public class CrackCreateRequest {

    private Float accuracy;
    private String imageUrl;
    private Float latitude;
    private Float longitude;
    private Float x;
    private Float y;
    private Address address;

    public CrackCreateRequest(Float accuracy, String imageUrl, CrackDetectRequest crackDetectRequest) {
        this.accuracy = accuracy;
        this.imageUrl = imageUrl;
        this.latitude = crackDetectRequest.getLatitude();
        this.longitude = crackDetectRequest.getLongitude();
        this.x = crackDetectRequest.getX();
        this.y = crackDetectRequest.getY();
        this.address = new Address(
            crackDetectRequest.getAddressName(),
            crackDetectRequest.getRoadAddressName(),
            crackDetectRequest.getRegion1DepthName(),
            crackDetectRequest.getRegion2DepthName(),
            crackDetectRequest.getRegion3DepthName(),
            crackDetectRequest.getZoneNo()
        );
    }
}
