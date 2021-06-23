package com.skillhunt.converter;

import com.skillhunt.db.course.CourseEntity;
import com.skill_hunt.web.dto.course.СourseDto;
import org.springframework.beans.BeanUtils;

public class CourseConverter {

    public static СourseDto toDto(CourseEntity entity) {
        СourseDto dto = new СourseDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static СourseDto toDtoNew(CourseEntity entity) {
        СourseDto dto = new СourseDto();
        dto.setFieldArea(FieldAreaConverter.toDto(entity.getFieldArea()));
        dto.setCompany(CompanyConverter.toDto(entity.getCompany()));
        dto.setDirection(DirectionConverter.toDto(entity.getDirection()));
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static CourseEntity toEntity(СourseDto dto) {
        CourseEntity entity = new CourseEntity();
        BeanUtils.copyProperties(dto, entity, "fieldAreaId", "companyId", "directionId");
        entity.setFieldAreaId(dto.getFieldAreaId());
        entity.setCompanyId(dto.getCompanyId());
        entity.setDirectionId(dto.getDirectionId());
        return entity;
    }
}
