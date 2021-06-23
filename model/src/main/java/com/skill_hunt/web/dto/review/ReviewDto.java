package com.skill_hunt.web.dto.review;

import com.skill_hunt.web.dto.course.СourseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private Long id;
    private int rating;
    private String comment;

    private СourseDto course;
    private Long courseId;

    public ReviewDto(int rating, String comment, СourseDto course, Long courseId){
        this.rating = rating;
        this.comment = comment;
        this.course = course;
        this.courseId = courseId;
    }
}
