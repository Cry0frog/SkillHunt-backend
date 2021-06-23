package com.skill_hunt.web.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {

    private Long id;
    private String companyName;

    public CompanyDto(String companyName) {
        this.companyName = companyName;
    }
}
