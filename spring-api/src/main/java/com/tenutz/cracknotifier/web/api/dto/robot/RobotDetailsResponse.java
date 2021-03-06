package com.tenutz.cracknotifier.web.api.dto.robot;

import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.util.TimeUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RobotDetailsResponse {

    private String seq;
    private String modelNo;
    private String image;
    private float width;
    private float depth;
    private float height;
    private float weight;
    private String createdAt;
    private String lastModifiedAt;

    public RobotDetailsResponse(Robot robot) {
        this.seq = robot.getSeq();
        this.modelNo = robot.getModelNo();
        this.image = robot.getImage();
        this.width = robot.getWidth();
        this.depth = robot.getDepth();
        this.height = robot.getHeight();
        this.weight = robot.getWeight();
        this.createdAt = TimeUtils.dateTimeFormatOf(robot.getCreatedAt());
        this.lastModifiedAt = TimeUtils.dateTimeFormatOf(robot.getLastModifiedAt());
    }
}
