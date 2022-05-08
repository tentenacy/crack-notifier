package com.tenutz.cracknotifier.domain.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

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
