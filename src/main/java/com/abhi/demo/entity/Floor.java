package com.abhi.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "floors")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // "Ground Floor", "1st Floor"

    private Integer number;     // 0, 1, 2...

    private String floorPlanImage;  // "floorplan_123456789.jpg"

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    // Constructors
    public Floor() {}

    public Floor(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }

    public String getFloorPlanImage() { return floorPlanImage; }
    public void setFloorPlanImage(String floorPlanImage) { this.floorPlanImage = floorPlanImage; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }
}
