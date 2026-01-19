package com.abhi.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    // 🆕 NEW: Store floor plan image filename
    private String floorPlanImage;

    // 🆕 TEMP: Comment out Floor until created
    // @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    // private List<Floor> floors = new ArrayList<>();

    // Constructors
    public Building() {}

    public Building(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // FIXED Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // FIXED: this.floorPlanImage (was missing 'this')
    public String getFloorPlanImage() { return floorPlanImage; }
    public void setFloorPlanImage(String floorPlanImage) {
        this.floorPlanImage = floorPlanImage;
    }

    // TEMP: Comment out until Floor.java created
    // public List<Floor> getFloors() { return floors; }
    // public void setFloors(List<Floor> floors) { this.floors = floors; }
}
