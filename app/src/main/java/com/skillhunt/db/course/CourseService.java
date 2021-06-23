package com.skillhunt.db.course;

import com.skillhunt.converter.CourseConverter;
import com.skillhunt.db.repository.CourseRepo;
import com.skillhunt.exceptions.EntityException;
import com.skillhunt.exceptions.helper.ErrorCode;
import com.skillhunt.storage.service.StorageException;
import com.skillhunt.storage.service.StorageFileNotFoundException;
import com.skillhunt.storage.service.StorageService;
import com.skill_hunt.web.dto.course.СourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final StorageService storageService;

    public СourseDto addСourse(СourseDto сourseDto) {
        CourseEntity courseEntity = CourseConverter.toEntity(сourseDto);
        return CourseConverter.toDto(courseRepo.save(courseEntity));
    }

    public СourseDto addPhotoCourse(MultipartFile file) {
        String uuidOriginalFilename = getUUIDOriginalFilename(file);
        storageService.store(file, uuidOriginalFilename);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setImageFilename(uuidOriginalFilename);
        return CourseConverter.toDto(courseRepo.save(courseEntity));
    }

    public СourseDto getCourse(Long id) {
        CourseEntity courseEntity = courseRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return CourseConverter.toDto(courseEntity);
    }

    public List<СourseDto> getAllCourses() {
        List<CourseEntity> courseEntitySet = courseRepo.getAllCourses();
        List<СourseDto> requestDtoSet = new ArrayList<>();
        for (CourseEntity courseEntity : courseEntitySet) {
            requestDtoSet.add(CourseConverter.toDtoNew(courseEntity));
        }
        return requestDtoSet;
    }

    public СourseDto changePublish(Long courseId, Boolean checked) {
        CourseEntity courseEntity = courseRepo.findById(courseId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));

        courseEntity.setPublish(checked);
        courseRepo.save(courseEntity);

        return CourseConverter.toDto(courseEntity);
    }

    public void removeCourse(Long courseId) {
        CourseEntity courseEntity = courseRepo.findById(courseId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        if (courseEntity.getImageFilename() != null) {
            deleteImg(courseEntity.getImageFilename());
        }
        courseRepo.delete(courseEntity);
    }

    private String getExpansion(MultipartFile file) {
        int beginIndExpansion = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        return file.getOriginalFilename().substring(beginIndExpansion);
    }

    public Resource getImg(Long id) {
        CourseEntity courseEntity = courseRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        Resource resource = null;
        try {

        } catch (StorageException e) {
            throw new StorageFileNotFoundException("file not found");
        }
        return storageService.loadAsResource(courseEntity.getImageFilename());
    }


    private String getUUIDOriginalFilename(MultipartFile file) {
        int beginIndExpansion = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String fileNewName = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename().substring(0, beginIndExpansion);
        return file.getOriginalFilename().replace(fileName, fileNewName);
    }

    public СourseDto changePhotoСourse(Long id, MultipartFile file) {
        CourseEntity courseEntity = courseRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        if (courseEntity.getImageFilename() != null) {
            deleteImg(courseEntity.getImageFilename());
        }

        String uuidOriginalFilename = getUUIDOriginalFilename(file);
        storageService.store(file, uuidOriginalFilename);
        courseEntity.setImageFilename(uuidOriginalFilename);

        return CourseConverter.toDto(courseRepo.save(courseEntity));
    }

    public СourseDto changeСourse(Long id) {
        CourseEntity courseEntity = courseRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        courseEntity.setName(courseEntity.getName());
        courseEntity.setLink(courseEntity.getLink());
        courseEntity.setDescription(courseEntity.getDescription());

        return CourseConverter.toDto(courseRepo.save(courseEntity));
    }

    public List<СourseDto> getPublishedCoursesWithCompany(long company_id) {
        return courseRepo.findAllByPublishWithCompany(company_id, true).stream().map(CourseConverter::toDtoNew).collect(Collectors.toList());
    }

    public List<СourseDto> getPublishedCoursesWithFieldArea(long fieldArea_id) {
        return courseRepo.findAllByPublishWithFieldArea(fieldArea_id, true).stream().map(CourseConverter::toDtoNew).collect(Collectors.toList());
    }

    public List<СourseDto> getPublishedCoursesWithDirection(long direction_id) {
        return courseRepo.findAllByPublishWithDirection(direction_id, true).stream().map(CourseConverter::toDtoNew).collect(Collectors.toList());
    }

    private void deleteImg(String imageFileName) {
        Resource oldImageСourse = storageService.loadAsResource(imageFileName);
        try {
            oldImageСourse.getFile().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
