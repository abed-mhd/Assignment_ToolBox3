package com.emse.spring.Assignment.dto;

import com.emse.spring.Assignment.model.Sensor;

public record WindowCommand(String name, Sensor windowStatus, Long roomId) {
}