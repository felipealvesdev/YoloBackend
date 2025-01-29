package com.yolo.backend.repositories;

import com.yolo.backend.domain.User;
import com.yolo.backend.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserType(UserType userType);
}
