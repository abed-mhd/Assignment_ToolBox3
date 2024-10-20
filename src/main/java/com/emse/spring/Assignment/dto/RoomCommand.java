package com.emse.spring.Assignment.dto;

import com.emse.spring.Assignment.model.Sensor;

public record RoomCommand(String name,int floor ,Sensor currentTemperature_id,double target_temperature) {
}