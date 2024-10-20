package com.emse.spring.Assignment.ControllerTest;

import com.emse.spring.Assignment.Controller.RoomController;
import com.emse.spring.Assignment.FakeEntityBuilder;
import com.emse.spring.Assignment.dao.RoomDao;
import com.emse.spring.Assignment.dto.RoomCommand;
import com.emse.spring.Assignment.model.Room;
import com.emse.spring.Assignment.model.SensorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomDao roomDao;

    @Test
    void shouldFindAllRooms() throws Exception {
        Mockito.when(roomDao.findAll()).thenReturn(List.of(
                FakeEntityBuilder.createRoomEntity(1L, "Room1"),
                FakeEntityBuilder.createRoomEntity(2L, "Room2")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Room1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Room2"));
    }

    @Test
    void shouldFindRoomById() throws Exception {
        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(FakeEntityBuilder.createRoomEntity(1L, "Room1")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Room1"));
    }

    @Test
    void shouldCreateRoom() throws Exception {
        Room room = FakeEntityBuilder.createRoomEntity(1L, "Room1");
        RoomCommand roomCommand = new RoomCommand("Room1", room.getFloor(), room.getCurrentTemperature_id(), room.getTargetTemperature());

        Mockito.when(roomDao.save(Mockito.any(Room.class))).thenReturn(room);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomCommand)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Room1"));
    }

    @Test
    void shouldUpdateRoom() throws Exception {
        Room existingRoom = FakeEntityBuilder.createRoomEntity(1L, "Room1");
        RoomCommand updatedRoomCommand = new RoomCommand("UpdatedRoom", existingRoom.getFloor(), existingRoom.getCurrentTemperature_id(), 24.5);

        Mockito.when(roomDao.findById(1L)).thenReturn(Optional.of(existingRoom));
        Mockito.when(roomDao.save(Mockito.any(Room.class))).thenReturn(existingRoom);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/rooms/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRoomCommand)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UpdatedRoom"));
    }

    @Test
    void shouldDeleteRoom() throws Exception {
        Mockito.doNothing().when(roomDao).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/rooms/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

