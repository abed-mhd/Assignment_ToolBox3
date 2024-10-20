package com.emse.spring.Assignment.mapper;

import com.emse.spring.Assignment.dto.SensorDTO;
import com.emse.spring.Assignment.model.Sensor;

public class SensorMapper {
    public static SensorDTO of(Sensor sensor) {
        return new SensorDTO(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType()
        );
    }
}