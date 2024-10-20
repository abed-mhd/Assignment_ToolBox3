package com.emse.spring.Assignment.mapper;

import com.emse.spring.Assignment.dto.RoomDTO;
import com.emse.spring.Assignment.dto.WindowDTO;
import com.emse.spring.Assignment.model.Room;
import com.emse.spring.Assignment.model.Window;

public class RoomMapper {
    public static RoomDTO of(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperature_id(),
                room.getTargetTemperature()
        );
    }
}