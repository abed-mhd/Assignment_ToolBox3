package com.emse.spring.Assignment.dto;

import com.emse.spring.Assignment.model.Sensor;

public record RoomDTO(Long id, String name,int floor ,Sensor currentTemperature_id,double target_temperature) {
}