package com.tenutz.cracknotifier.web.api.dto.robot;

import com.tenutz.cracknotifier.domain.robot.Robot;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RobotCreateResponse {

    private String seq;
    private String modelNo;

    public RobotCreateResponse(Robot robot) {
        this.seq = robot.getSeq();
        this.modelNo = robot.getModelNo();
    }
}
