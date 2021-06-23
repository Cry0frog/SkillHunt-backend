package com.skillhunt.controller;

import com.skill_hunt.web.dto.direction.DirectionDto;
import com.skillhunt.db.direction.DirectionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Direction"})
@RestController
@RequestMapping(value = "/directions")
public class DirectionController {

    private final DirectionService directionService;

    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    @PostMapping("/add/direction")
    @ResponseBody
    public DirectionDto addDirection(@RequestBody DirectionDto directionDto) {
        return directionService.addDirection(directionDto);
    }

    @GetMapping("")
    @ResponseBody
    public List<DirectionDto> getAllDirection() {
        return directionService.getAllDirection();
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void removeDirection(@PathVariable("id") Long directionId) {
        directionService.removeDirection(directionId);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DirectionDto getDirection(@PathVariable("id") Long directionId) {
        return directionService.getDirection(directionId);
    }
}
