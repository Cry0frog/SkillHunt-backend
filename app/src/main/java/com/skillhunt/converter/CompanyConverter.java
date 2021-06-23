package com.skillhunt.converter;

import com.skill_hunt.web.dto.company.CompanyDto;
import com.skillhunt.db.company.CompanyEntity;
import org.springframework.beans.BeanUtils;

public class CompanyConverter {

    public static CompanyDto toDto(CompanyEntity entry) {
        CompanyDto dto = new CompanyDto();
        BeanUtils.copyProperties(entry, dto);
        return dto;
    }

    public static CompanyEntity toEntity(CompanyDto dto) {
        CompanyEntity entity = new CompanyEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
