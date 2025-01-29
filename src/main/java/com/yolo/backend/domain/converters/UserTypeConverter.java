package com.yolo.backend.domain.converters;

import com.yolo.backend.domain.enums.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {
    @Override
    public String convertToDatabaseColumn(UserType userType) {
        if (userType == null) return null;
        return userType.getType();
    }

    @Override
    public UserType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return UserType.fromString(dbData);
    }
}
