package com.spring.security.practice.springsecuritypractice.member.common.converter;

import javax.persistence.AttributeConverter;
import java.util.List;

public class RoleConverter implements AttributeConverter<List<Enum>, String> {
    @Override
    public String convertToDatabaseColumn(List<Enum> enums) {
        return null;
    }

    @Override
    public List<Enum> convertToEntityAttribute(String s) {
        return null;
    }
}
