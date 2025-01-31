package com.yolo.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yolo.backend.domain.enums.UserType;

import java.time.LocalDate;

public record UserDTOInitialDb(Long id,
                               @JsonProperty("Nome") String name,
                               @JsonProperty("Telefone") String phoneNumber,
                               @JsonProperty("E-mail") String email,
                               @JsonProperty("Tipo") UserType userType,
                               @JsonProperty("Data de Cadastro") LocalDate createdAt) {
}
