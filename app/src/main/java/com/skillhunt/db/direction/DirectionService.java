package com.skillhunt.db.direction;

import com.skill_hunt.web.dto.direction.DirectionDto;
import com.skillhunt.converter.DirectionConverter;
import com.skillhunt.db.repository.DirectionRepo;
import com.skillhunt.exceptions.EntityException;
import com.skillhunt.exceptions.helper.ErrorCode;
import com.skillhunt.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionService {

    private final DirectionRepo directionRepo;
    private final StorageService storageService;

    public DirectionDto addDirection(DirectionDto directionDto) {
        DirectionEntity directionEntity = DirectionConverter.toEntity(directionDto);
        return DirectionConverter.toDto(directionRepo.save(directionEntity));
    }

    public List<DirectionDto> getAllDirection() {
        List<DirectionEntity> directionEntitySet = directionRepo.findAll(Sort.by("id"));
        List<DirectionDto> requestDtoSet = new ArrayList<>();
        for (DirectionEntity directionEntity : directionEntitySet) {
            requestDtoSet.add(DirectionConverter.toDto(directionEntity));
        }
        return requestDtoSet;
    }

    public DirectionDto getDirection(Long id) {
        DirectionEntity directionEntity = directionRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return DirectionConverter.toDto(directionEntity);
    }

    public void removeDirection(Long directionId) {
        DirectionEntity directionEntity = directionRepo.findById(directionId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        directionRepo.delete(directionEntity);
    }
}
