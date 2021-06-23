package com.skill_hunt.web.dto.direction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class DirectionDto {

    private Long id;

    @NotNull(message = "{validation-message.direction.not-null}")
    private String directionName;

    public DirectionDto(String directionName){
        this.directionName = directionName;
    }
}
