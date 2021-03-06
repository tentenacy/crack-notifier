package com.tenutz.cracknotifier.domain.user;

import com.tenutz.cracknotifier.domain.robot.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserQueryRepository {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndProvider(String email, String provider);
    Optional<User> findByRobot(Robot robot);
}