package com.skill_hunt.web.dto.course;

import com.skill_hunt.web.dto.company.CompanyDto;
import com.skill_hunt.web.dto.direction.DirectionDto;
import com.skill_hunt.web.dto.field_area.FieldAreaDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class СourseDto {

    private Long id;
    private String name;
    private String link;
    private String description;
    private String imageFilename;
    private boolean publish;

    private Long directionId;
    private DirectionDto direction;
    private Long fieldAreaId;
    private FieldAreaDto fieldArea;
    private Long companyId;
    private CompanyDto company;

    public СourseDto(String name, String link, String description, String imageFilename,
                     boolean publish, DirectionDto direction, Long directionId,
                     FieldAreaDto fieldArea, Long fieldAreaId,
                     CompanyDto company, Long companyId){
        this.name = name;
        this.link = link;
        this.description = description;
        this.imageFilename = imageFilename;
        this.publish = publish;
        this.direction = direction;
        this.directionId = directionId;
        this.fieldArea = fieldArea;
        this.fieldAreaId = fieldAreaId;
        this.company = company;
        this.companyId = companyId;
    }
}
