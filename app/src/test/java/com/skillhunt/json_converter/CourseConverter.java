package com.skillhunt.json_converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill_hunt.web.dto.course.СourseDto;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

public class CourseConverter {

    @SneakyThrows
    public static СourseDto convertJsonToCourseDto(String path){
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(path).getFile();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.reader().forType(СourseDto.class).readValue(jsonFile);
    }
}
