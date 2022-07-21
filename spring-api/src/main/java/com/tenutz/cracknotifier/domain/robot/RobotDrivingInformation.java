package com.tenutz.cracknotifier.domain.robot;

import com.tenutz.cracknotifier.domain.base.BaseTimeEntity;
import com.tenutz.cracknotifier.util.manager.SeqManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class RobotDrivingInformation extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_rdif")
    @GenericGenerator(name = "seq_manager_rdif", strategy = "com.tenutz.cracknotifier.util.manager.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "rdif_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "robot_seq", nullable = false)
    private Robot robot;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float latitude;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float longitude;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float x;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float y;

    @Column
    private String addressName;

    @Column
    private String roadAddressName;

    @Column
    private String region1DepthName;

    @Column
    private String region2DepthName;

    @Column
    private String region3DepthName;

    @Column
    private String zoneNo;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float velocity;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float propellerVelocity;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float slopeX;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float slopeY;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float slopeZ;

    @Column(columnDefinition = "float(11, 4) default 0.0000")
    private float batteryVoltage;

    public static RobotDrivingInformation create(Robot robot) {
        RobotDrivingInformation robotDrivingInformation = new RobotDrivingInformation();
        robotDrivingInformation.setRobot(robot);
        return robotDrivingInformation;
    }

    public void update(float latitude, float longitude, float x, float y, String addressName, String roadAddressName, String region1DepthName, String region2DepthName, String region3DepthName, String zoneNo, float velocity, float propellerVelocity, float slopeX, float slopeY, float slopeZ, float batteryVoltage) {
        setLatitude(latitude);
        setLongitude(longitude);
        setX(x);
        setY(y);
        setAddressName(addressName);
        setRoadAddressName(roadAddressName);
        setRegion1DepthName(region1DepthName);
        setRegion2DepthName(region2DepthName);
        setRegion3DepthName(region3DepthName);
        setZoneNo(zoneNo);
        setVelocity(velocity);
        setPropellerVelocity(propellerVelocity);
        setSlopeX(slopeX);
        setSlopeY(slopeY);
        setSlopeZ(slopeZ);
        setBatteryVoltage(batteryVoltage);
    }
}
