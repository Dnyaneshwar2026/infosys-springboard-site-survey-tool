
package com.isp.survey.space;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    private final SpaceRepository spaceRepository;

    public SpaceController(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @GetMapping
    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    @PostMapping
    public Space createSpace(@RequestBody Space space) {
        return spaceRepository.save(space);
    }
}
