package com.emse.spring.Assignment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_WINDOW")
public class Window {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor windowStatus;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Window() {
    }

    public Window(String name, Sensor windowStatus, Room room) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Sensor getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(Sensor windowStatus) {
        this.windowStatus = windowStatus;
    }
}
