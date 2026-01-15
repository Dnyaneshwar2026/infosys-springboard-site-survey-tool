package com.isp.survey.floor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isp.survey.building.Building;
import com.isp.survey.building.BuildingRepository;

@RestController
@RequestMapping("/floors")
public class FloorController {

    private final FloorRepository floorRepository;
    private final BuildingRepository buildingRepository;

    public FloorController(FloorRepository floorRepository,
                           BuildingRepository buildingRepository) {
        this.floorRepository = floorRepository;
        this.buildingRepository = buildingRepository;
    }

    @PostMapping("/{buildingId}")
    public Floor createFloor(
            @PathVariable Long buildingId,
            @RequestBody Floor floor) {

        Building building = buildingRepository.findById(buildingId).orElseThrow();
        floor.setBuilding(building);

        return floorRepository.save(floor);
    }
}
