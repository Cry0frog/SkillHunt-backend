package com.skillhunt.db.repository;

import com.skillhunt.db.course.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<CourseEntity, Long> {

    @Query("SELECT course FROM CourseEntity course " +
            "LEFT JOIN FETCH course.direction direction " +
            "LEFT JOIN FETCH course.fieldArea fieldArea " +
            "LEFT JOIN FETCH course.company company " +
            "WHERE course.publish = true")
    List<CourseEntity> getAllCourses();

    @Query("SELECT course FROM CourseEntity course " +
            "LEFT JOIN FETCH course.direction direction " +
            "LEFT JOIN FETCH course.fieldArea fieldArea " +
            "LEFT JOIN FETCH course.company company " +
            "WHERE course.directionId = :direction_id AND course.publish = :isPublish")
    List<CourseEntity> findAllByPublishWithDirection(long direction_id, boolean isPublish);

    @Query("SELECT course FROM CourseEntity course " +
            "LEFT JOIN FETCH course.direction direction " +
            "LEFT JOIN FETCH course.fieldArea fieldArea " +
            "LEFT JOIN FETCH course.company company " +
            "WHERE course.companyId = :company_id AND course.publish = :isPublish")
    List<CourseEntity> findAllByPublishWithCompany(long company_id, boolean isPublish);

    @Query("SELECT course FROM CourseEntity course " +
            "LEFT JOIN FETCH course.direction direction " +
            "LEFT JOIN FETCH course.fieldArea fieldArea " +
            "LEFT JOIN FETCH course.company company " +
            "WHERE course.fieldAreaId = :fieldArea_id AND course.publish = :isPublish")
    List<CourseEntity> findAllByPublishWithFieldArea(long fieldArea_id, boolean isPublish);
}
