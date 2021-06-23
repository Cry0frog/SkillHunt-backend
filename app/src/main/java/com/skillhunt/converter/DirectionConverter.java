package com.skillhunt.converter;

import com.skill_hunt.web.dto.direction.DirectionDto;
import com.skillhunt.db.direction.DirectionEntity;
import org.springframework.beans.BeanUtils;

public class DirectionConverter {

    public static DirectionDto toDto(DirectionEntity entry) {
        DirectionDto dto = new DirectionDto();
        BeanUtils.copyProperties(entry, dto);
        return dto;
    }

    public static DirectionEntity toEntity(DirectionDto dto) {
        DirectionEntity entity = new DirectionEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
