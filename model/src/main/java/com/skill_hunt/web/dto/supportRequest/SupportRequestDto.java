package com.skill_hunt.web.dto.supportRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportRequestDto {
    private Long id;
    private String message;
    private String username;
    private String date;
}
