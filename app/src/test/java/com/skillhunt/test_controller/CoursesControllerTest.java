package com.skillhunt.test_controller;

import com.skillhunt.db.repository.CourseRepo;
import com.skillhunt.json_converter.CourseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CoursesControllerTest {

    @Autowired
    private CourseRepo categoryRepo;

    @Autowired
    private CourseConverter categoryConverter;
}
