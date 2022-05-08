package com.tenutz.cracknotifier.web.api.dto.robot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RobotCreateRequest {

    @NotNull
    private float width;
    @NotNull
    private float depth;
    @NotNull
    private float height;
    @NotNull
    private float weight;
}
