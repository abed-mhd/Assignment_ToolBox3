package com.emse.spring.Assignment.dto;

import com.emse.spring.Assignment.model.Sensor;

public record WindowDTO(Long id, String name, Sensor windowStatus, Long roomId) {
}