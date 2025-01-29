package com.yolo.backend.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

    SUPPLIER("Fornecedor"),
    OWNER("Proprietário"),
    OPERATOR("Operador"),
    GUEST("Hóspede");

    private String type;
    UserType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static UserType fromString(String type) {
        for (UserType userType : UserType.values()) {
            if (userType.type.equalsIgnoreCase(type)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário desconhecido: " + type);
    }


}
