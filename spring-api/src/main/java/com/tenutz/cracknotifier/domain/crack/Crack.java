package com.tenutz.cracknotifier.domain.crack;

import com.tenutz.cracknotifier.domain.common.Address;
import com.tenutz.cracknotifier.domain.robot.Robot;
import com.tenutz.cracknotifier.util.manager.SeqManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class Crack {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_crak")
    @GenericGenerator(name = "seq_manager_crak", strategy = "com.tenutz.cracknotifier.util.manager.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "crak_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String seq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "robot_seq")
    private Robot robot;

    @Column(nullable = false, columnDefinition = "float(7, 4) default 0.0000")
    private float accuracy;

    private String image;

    @Column(nullable = false, columnDefinition = "float(13, 10) default 0.0000000000")
    private float latitude;

    @Column(nullable = false, columnDefinition = "float(13, 10) default 0.0000000000")
    private float longitude;

    @Column(nullable = false, columnDefinition = "float(7, 4) default 0.0000")
    private float x;

    @Column(nullable = false, columnDefinition = "float(7, 4) default 0.0000")
    private float y;

    @Embedded
    private Address address;

    public static Crack create(Robot robot, float accuracy, String image, float latitude, float longitude, float x, float y, Address address) {
        Crack crack = new Crack();
        crack.setRobot(robot);
        crack.setAccuracy(accuracy);
        crack.setImage(image);
        crack.setLatitude(latitude);
        crack.setLongitude(longitude);
        crack.setX(x);
        crack.setY(y);
        crack.setAddress(address);
        return crack;
    }
}
