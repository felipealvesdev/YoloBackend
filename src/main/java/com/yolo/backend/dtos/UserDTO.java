package com.yolo.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yolo.backend.domain.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDTO(
                      String name,
                      String phoneNumber,
                      String email,
                      UserType userType,
                      LocalDate createdAt) {
}
