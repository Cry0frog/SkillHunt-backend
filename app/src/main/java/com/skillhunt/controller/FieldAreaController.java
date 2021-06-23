package com.skillhunt.controller;

import com.skill_hunt.web.dto.field_area.FieldAreaDto;
import com.skillhunt.db.field_area.FieldAreaService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"FieldArea"})
@RestController
@RequestMapping(value = "/fields_area")
public class FieldAreaController {
    private final FieldAreaService fieldAreaService;

    public FieldAreaController(FieldAreaService fieldAreaService) {
        this.fieldAreaService = fieldAreaService;
    }

    @PostMapping("/add/field_area")
    @ResponseBody
    public FieldAreaDto addFieldArea(@RequestBody FieldAreaDto fieldAreaDto) {
        return fieldAreaService.addFieldArea(fieldAreaDto);
    }

    @GetMapping("")
    @ResponseBody
    public List<FieldAreaDto> getAllFieldArea() {
        return fieldAreaService.getAllFieldArea();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void removeFieldArea(@PathVariable("id") Long fieldAreaId) {
        fieldAreaService.removeFieldArea(fieldAreaId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FieldAreaDto getFieldArea(@PathVariable("id") Long fieldAreaId) {
        return fieldAreaService.getFieldArea(fieldAreaId);
    }
}
