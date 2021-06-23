package com.skillhunt.converter;

import com.skill_hunt.web.dto.field_area.FieldAreaDto;
import com.skillhunt.db.field_area.FieldAreaEntity;
import org.springframework.beans.BeanUtils;

public class FieldAreaConverter {

    public static FieldAreaDto toDto(FieldAreaEntity entry) {
        FieldAreaDto dto = new FieldAreaDto();
        BeanUtils.copyProperties(entry, dto);
        return dto;
    }

    public static FieldAreaEntity toEntity(FieldAreaDto dto) {
        FieldAreaEntity entity = new FieldAreaEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
