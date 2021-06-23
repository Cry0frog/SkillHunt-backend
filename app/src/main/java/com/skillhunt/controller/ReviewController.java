package com.skillhunt.controller;

import com.skill_hunt.web.dto.review.ReviewDto;
import com.skillhunt.db.review.ReviewService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Review"})
@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/review")
    @ResponseBody
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(reviewDto);
    }

    @GetMapping("")
    @ResponseBody
    public List<ReviewDto> getAllReview() {
        return reviewService.getAllReview();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void removeReview(@PathVariable("id") Long reviewId) {
        reviewService.removeReview(reviewId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ReviewDto getReview(@PathVariable("id") Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    @GetMapping("{id}/course")
    @ResponseBody
    public List<ReviewDto> getPublishedReviewWithCourse(@PathVariable("id") long course_id) {
        return reviewService.getPublishedReviewsWithCourse(course_id);
    }
}
