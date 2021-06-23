package com.skillhunt.db.supportRequest;

import com.skillhunt.converter.SupportRequestConverter;
import com.skillhunt.db.repository.SupportRequestRepo;
import com.skillhunt.exceptions.EntityException;
import com.skillhunt.exceptions.helper.ErrorCode;
import com.skill_hunt.web.dto.supportRequest.SupportRequestDto;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SupportRequestService {
    private final SupportRequestRepo supportRequestRepo;
    public SupportRequestService(SupportRequestRepo supportRequestRepo){
        this.supportRequestRepo = supportRequestRepo;
    }

    public SupportRequestEntity saveSupportMessage(SupportRequestDto supportRequestDto){
        SupportRequestEntity requestEntity = SupportRequestConverter.toEntity(supportRequestDto);
        requestEntity.setDate(getDateNow(new SimpleDateFormat("dd-MM-yyyy")));
        return supportRequestRepo.save(requestEntity);

    }

    public SupportRequestDto getRequest(Long id){
        SupportRequestEntity supportRequestEntity = supportRequestRepo.findById(id).orElseThrow(() -> new EntityException(ErrorCode.COMMON_OBJECT_NOT_EXISTS, "not found entity"));
        return SupportRequestConverter.toDto(supportRequestEntity);
    }

    public List<SupportRequestDto> getAllRequest(){
        List<SupportRequestEntity> supportRequestEntityList = supportRequestRepo.findAll();
        List<SupportRequestDto> supportRequestDtoList = new ArrayList<>();
        for (SupportRequestEntity supportRequestEntity: supportRequestEntityList) {
            supportRequestDtoList.add(SupportRequestConverter.toDto(supportRequestEntity));
        }
        return supportRequestDtoList;

    }

    public void deleteRequest(Long id){
        Optional<SupportRequestEntity> supportRequestEntityOptional = supportRequestRepo.findById(id);
        if (supportRequestEntityOptional.isPresent()){
            SupportRequestEntity supportRequestEntity = supportRequestEntityOptional.get();
            supportRequestRepo.delete(supportRequestEntity);
        }else{
            throw new EntityException(ErrorCode.JAVA_ERROR, "The entity not found");
        }

    }

    private String getDateNow(SimpleDateFormat simpleDateFormat){
        Date currentDate = new Date();
        return simpleDateFormat.format(currentDate);
    }
}
