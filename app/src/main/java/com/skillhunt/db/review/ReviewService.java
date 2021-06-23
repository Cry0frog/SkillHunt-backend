package com.skillhunt.db.review;

import com.skill_hunt.web.dto.review.ReviewDto;
import com.skillhunt.converter.ReviewConverter;
import com.skillhunt.db.repository.ReviewRepo;
import com.skillhunt.exceptions.EntityException;
import com.skillhunt.exceptions.helper.ErrorCode;
import com.skillhunt.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final StorageService storageService;

    public ReviewDto addReview(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = ReviewConverter.toEntity(reviewDto);
        return ReviewConverter.toDto(reviewRepo.save(reviewEntity));
    }

    public List<ReviewDto> getAllReview() {
        List<ReviewEntity> reviewEntitySet = reviewRepo.getAllReview();
        List<ReviewDto> requestDtoSet = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntitySet) {
            requestDtoSet.add(ReviewConverter.toDtoNew(reviewEntity));
        }
        return requestDtoSet;
    }

    public ReviewDto getReview(Long id) {
        ReviewEntity reviewEntity = reviewRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return ReviewConverter.toDto(reviewEntity);
    }

    public void removeReview(Long reviewId) {
        ReviewEntity reviewEntity = reviewRepo.findById(reviewId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        reviewRepo.delete(reviewEntity);
    }

    public List<ReviewDto> getPublishedReviewsWithCourse(long course_id) {
        return reviewRepo.findAllWithCourse(course_id).stream().map(ReviewConverter::toDtoNew).collect(Collectors.toList());
    }

}
