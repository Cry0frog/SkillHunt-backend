package com.skillhunt.db.repository;

import com.skillhunt.db.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT review FROM ReviewEntity review " +
            "LEFT JOIN FETCH review.course course")
    List<ReviewEntity> getAllReview();

    @Query("SELECT review FROM ReviewEntity review " +
            "LEFT JOIN FETCH review.course course " +
            "WHERE review.courseId = :course_id")
    List<ReviewEntity> findAllWithCourse(long course_id);
}
