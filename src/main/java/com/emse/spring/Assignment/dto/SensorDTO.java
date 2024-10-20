package com.emse.spring.Assignment.dto;

import com.emse.spring.Assignment.model.SensorType;

public record SensorDTO(Long id, String name, Double value, SensorType sensorType) {
}
