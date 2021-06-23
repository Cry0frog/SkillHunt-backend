package com.skillhunt.controller;

import com.skillhunt.db.course.CourseService;
import com.skill_hunt.web.dto.course.СourseDto;
import io.swagger.annotations.Api;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = {"Сourse"})
@RestController
@RequestMapping(value = "/courses")
public class СourseController {

    private final CourseService courseService;

    public СourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add/course")
    @ResponseBody
    public СourseDto addСourse(@RequestBody СourseDto сourseDto) {
        return courseService.addСourse(сourseDto);
    }

    @PostMapping("{id}/change/image")
    @ResponseBody
    public СourseDto changeImageСourse(@PathVariable("id") Long id, @RequestParam("image") MultipartFile form) {
        return courseService.changePhotoСourse(id, form);
    }

    @PostMapping("{id}/change")
    @ResponseBody
    public СourseDto changeСourse(@PathVariable("id") Long id) {
        return courseService.changeСourse(id);
    }

    @PostMapping("/add/image")
    @ResponseBody
    public СourseDto addImage(@RequestParam("image") MultipartFile form) {
        return courseService.addPhotoCourse(form);
    }

    @GetMapping("")
    @ResponseBody
    public List<СourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("{id}/change/publish")
    @ResponseBody
    public СourseDto changePublish(@PathVariable("id") Long courseId, @RequestParam("eventChecked") Boolean checked) {
        return courseService.changePublish(courseId, checked);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void removePublish(@PathVariable("id") Long courseId) {
        courseService.removeCourse(courseId);
    }

    @GetMapping("/{id}/image")
    @ResponseBody
    public Resource getImgCourse(@PathVariable("id") Long courseId) {
        return courseService.getImg(courseId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public СourseDto getCourse(@PathVariable("id") Long courseId) {
        return courseService.getCourse(courseId);
    }

    @GetMapping("{id}/company/published")
    @ResponseBody
    public List<СourseDto> getPublishedСoursesWithCompany(@PathVariable("id") long company_id) {
        return courseService.getPublishedCoursesWithCompany(company_id);
    }

    @GetMapping("{id}/field_area/published")
    @ResponseBody
    public List<СourseDto> getPublishedСoursesWithFieldArea(@PathVariable("id") long fieldArea_id) {
        return courseService.getPublishedCoursesWithFieldArea(fieldArea_id);
    }

    @GetMapping("{id}/direction/published")
    @ResponseBody
    public List<СourseDto> getPublishedСoursesWithDirection(@PathVariable("id") long direction_id) {
        return courseService.getPublishedCoursesWithDirection(direction_id);
    }

}
