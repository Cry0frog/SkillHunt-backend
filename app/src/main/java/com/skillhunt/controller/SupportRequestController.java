package com.skillhunt.controller;

import com.skillhunt.converter.SupportRequestConverter;
import com.skillhunt.db.supportRequest.SupportRequestService;
import com.skill_hunt.web.dto.supportRequest.SupportRequestDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"Запрос расчета цены"})
@RestController
@RequestMapping(value = "/request")
public class SupportRequestController {
    private final SupportRequestService supportRequestService;
    public SupportRequestController(SupportRequestService supportRequestService) {
        this.supportRequestService = supportRequestService;
    }

    @PostMapping("/supportRequest")
    @ResponseBody
    public SupportRequestDto postRequest(@RequestBody SupportRequestDto supportRequestDto){
        return SupportRequestConverter.toDto(supportRequestService.saveSupportMessage(supportRequestDto));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SupportRequestDto getRequest(@PathVariable("id") Long requestId) {
        return supportRequestService.getRequest(requestId);
    }

    @GetMapping("")
    @ResponseBody
    public List<SupportRequestDto> getAllRequests(){
        return supportRequestService.getAllRequest();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void deleteRequest(@PathVariable Long id){
        supportRequestService.deleteRequest(id);
    }
}
