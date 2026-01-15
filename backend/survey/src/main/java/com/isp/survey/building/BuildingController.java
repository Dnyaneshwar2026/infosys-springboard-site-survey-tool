package com.isp.survey.building;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isp.survey.campus.Campus;
import com.isp.survey.campus.CampusRepository;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingRepository buildingRepository;
    private final CampusRepository campusRepository;

    public BuildingController(BuildingRepository buildingRepository,
                              CampusRepository campusRepository) {
        this.buildingRepository = buildingRepository;
        this.campusRepository = campusRepository;
    }

    @PostMapping("/{campusId}")
    public Building createBuilding(
            @PathVariable Long campusId,
            @RequestBody Building building) {

        Campus campus = campusRepository.findById(campusId).orElseThrow();
        building.setCampus(campus);

        return buildingRepository.save(building);
    }
}
