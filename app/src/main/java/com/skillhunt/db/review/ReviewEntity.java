package com.skillhunt.db.review;

import com.skillhunt.db.course.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="review")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable=false, insertable = false, updatable = false)
    private CourseEntity course;

    @Column(name="course_id")
    private Long courseId;
}
