package com.emse.spring.Assignment.mapper;

import com.emse.spring.Assignment.dto.SensorDTO;
import com.emse.spring.Assignment.dto.WindowDTO;
import com.emse.spring.Assignment.model.Sensor;
import com.emse.spring.Assignment.model.Window;

public class WindowMapper {
    public static WindowDTO of(Window window) {
        return new WindowDTO(
                window.getId(),
                window.getName(),
                window.getWindowStatus(),
                window.getRoom().getId()
        );
    }
}