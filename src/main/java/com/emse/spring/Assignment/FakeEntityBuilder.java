package com.emse.spring.Assignment;

import com.emse.spring.Assignment.model.Room;
import com.emse.spring.Assignment.model.Sensor;
import com.emse.spring.Assignment.model.SensorType;
import com.emse.spring.Assignment.model.Window;

import java.util.List;
import java.util.Set;

public class FakeEntityBuilder {

    public static Room createRoomEntity(Long id, String name) {
        // Sensor is recreated before each test
        Room room = new Room(1,
                name,
                createSensorEntity(1L, "Temp", SensorType.TEMPERATURE, 23.2),25.0);

        room.setTargetTemperature(26.4);
        room.setId(id);
        room.setWindows(List.of(
                createWindowEntity(id * 10 + 1L, "Window1" + name, room),
                createWindowEntity(id * 10 + 2L, "Window2" + name, room)
        ));
        return room;
    }

    public static Window createWindowEntity(Long id, String name, Room room) {
        // Sensor is recreated before each test
        Window window = new Window(
                name,
                createSensorEntity(id * 10 + 1L, "Status" + id, SensorType.STATUS, 0.0),
                room
        );
        window.setId(id);
        return window;
    }

    public static Sensor createSensorEntity(Long id, String name, SensorType type, Double value) {
        // Sensor is recreated before each test
        Sensor sensor = new Sensor(type, name);
        sensor.setId(id);
        sensor.setValue(value);
        return sensor;
    }
}