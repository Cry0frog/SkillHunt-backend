package com.skillhunt.converter;

import com.skillhunt.db.supportRequest.SupportRequestEntity;
import com.skill_hunt.web.dto.supportRequest.SupportRequestDto;
import org.springframework.beans.BeanUtils;

public class SupportRequestConverter {
    public static SupportRequestDto toDto(SupportRequestEntity entry) {
        SupportRequestDto dto = new SupportRequestDto();
        BeanUtils.copyProperties(entry, dto);
        return dto;
    }
    public static SupportRequestEntity toEntity(SupportRequestDto dto) {
        SupportRequestEntity entity = new SupportRequestEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
