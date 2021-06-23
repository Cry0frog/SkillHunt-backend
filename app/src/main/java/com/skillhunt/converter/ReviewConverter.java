package com.skillhunt.converter;

import com.skill_hunt.web.dto.review.ReviewDto;
import com.skillhunt.db.review.ReviewEntity;
import org.springframework.beans.BeanUtils;

public class ReviewConverter {

    public static ReviewDto toDto(ReviewEntity entity) {
        ReviewDto dto = new ReviewDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ReviewDto toDtoNew(ReviewEntity entity) {
        ReviewDto dto = new ReviewDto();
        dto.setCourse(CourseConverter.toDto(entity.getCourse()));
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ReviewEntity toEntity(ReviewDto dto) {
        ReviewEntity entity = new ReviewEntity();
        BeanUtils.copyProperties(dto, entity, "courseId");
        entity.setCourseId(dto.getCourseId());
        return entity;
    }
}
