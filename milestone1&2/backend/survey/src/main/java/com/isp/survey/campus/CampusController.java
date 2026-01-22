package com.isp.survey.campus;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campuses")
public class CampusController {

    private final CampusRepository campusRepository;

    public CampusController(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @PostMapping
    public Campus create(@RequestBody Campus campus) {
        return campusRepository.save(campus);
    }

    @GetMapping
    public List<Campus> getAll() {
        return campusRepository.findAll();
    }
}
