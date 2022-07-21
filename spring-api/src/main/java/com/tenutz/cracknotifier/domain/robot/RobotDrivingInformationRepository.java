package com.tenutz.cracknotifier.domain.robot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RobotDrivingInformationRepository extends JpaRepository<RobotDrivingInformation, String> {
    Optional<RobotDrivingInformation> findByRobot(Robot robot);
}
