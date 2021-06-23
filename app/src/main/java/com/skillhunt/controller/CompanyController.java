package com.skillhunt.controller;

import com.skill_hunt.web.dto.company.CompanyDto;
import com.skillhunt.db.company.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Company"})
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/add/company")
    @ResponseBody
    public CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @GetMapping("")
    @ResponseBody
    public List<CompanyDto> getAllCompany() {
        return companyService.getAllCompany();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void removeCompany(@PathVariable("id") Long companyId) {
        companyService.removeCompany(companyId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CompanyDto getCompany(@PathVariable("id") Long companyId) {
        return companyService.getCompany(companyId);
    }
}
