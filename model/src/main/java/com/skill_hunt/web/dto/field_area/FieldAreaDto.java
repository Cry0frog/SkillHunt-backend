package com.skill_hunt.web.dto.field_area;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FieldAreaDto {

    private Long id;
    private String fieldArea;

    public FieldAreaDto(String fieldArea) {
        this.fieldArea = fieldArea;
    }
}
