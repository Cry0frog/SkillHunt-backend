package com.skillhunt.db.company;

import com.skill_hunt.web.dto.company.CompanyDto;
import com.skillhunt.converter.CompanyConverter;
import com.skillhunt.db.repository.CompanyRepo;
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
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final StorageService storageService;

    public CompanyDto addCompany(CompanyDto companyDto) {
        CompanyEntity companyEntity = CompanyConverter.toEntity(companyDto);
        return CompanyConverter.toDto(companyRepo.save(companyEntity));
    }

    public List<CompanyDto> getAllCompany() {
        List<CompanyEntity> companyEntitySet = companyRepo.findAll(Sort.by("id"));
        List<CompanyDto> requestDtoSet = new ArrayList<>();
        for (CompanyEntity companyEntity : companyEntitySet) {
            requestDtoSet.add(CompanyConverter.toDto(companyEntity));
        }
        return requestDtoSet;
    }

    public CompanyDto getCompany(Long id) {
        CompanyEntity companyEntity = companyRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return CompanyConverter.toDto(companyEntity);
    }

    public void removeCompany(Long companyId) {
        CompanyEntity companyEntity = companyRepo.findById(companyId).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        companyRepo.delete(companyEntity);
    }
}
