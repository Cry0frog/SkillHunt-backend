package com.skillhunt.db.field_area;

import com.skill_hunt.web.dto.field_area.FieldAreaDto;
import com.skillhunt.converter.FieldAreaConverter;
import com.skillhunt.db.repository.FieldAreaRepo;
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
public class FieldAreaService {

    private final FieldAreaRepo fieldAreaRepo;
    private final StorageService storageService;

    public FieldAreaDto addFieldArea(FieldAreaDto fieldAreaDto) {
        FieldAreaEntity fieldAreaEntity = FieldAreaConverter.toEntity(fieldAreaDto);
        return FieldAreaConverter.toDto(fieldAreaRepo.save(fieldAreaEntity));
    }

    public List<FieldAreaDto> getAllFieldArea() {
        List<FieldAreaEntity> fieldAreaEntitySet = fieldAreaRepo.findAll(Sort.by("id"));
        List<FieldAreaDto> requestDtoSet = new ArrayList<>();
        for (FieldAreaEntity fieldAreaEntity : fieldAreaEntitySet) {
            requestDtoSet.add(FieldAreaConverter.toDto(fieldAreaEntity));
        }
        return requestDtoSet;
    }

    public FieldAreaDto getFieldArea(Long id) {
        FieldAreaEntity fieldAreaEntity = fieldAreaRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return FieldAreaConverter.toDto(fieldAreaEntity);
    }

    public void removeFieldArea(Long fieldAreaId) {
        FieldAreaEntity fieldAreaEntity = fieldAreaRepo.findById(fieldAreaId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        fieldAreaRepo.delete(fieldAreaEntity);
    }
}
