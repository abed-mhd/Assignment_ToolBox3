package com.emse.spring.Assignment.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SP_ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor currentTemperature_id;

    @Column(nullable = false)
    private Double targetTemperature;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Window> windows;

    public Room() {
    }

    public Room(Integer floor, String name, Sensor currentTemperature_id, Double targetTemperature) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature_id = currentTemperature_id;
        this.targetTemperature = targetTemperature;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sensor getCurrentTemperature_id() {
        return currentTemperature_id;
    }

    public void setCurrentTemperature(Sensor currentTemperature_id) {
        this.currentTemperature_id = currentTemperature_id;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }
}