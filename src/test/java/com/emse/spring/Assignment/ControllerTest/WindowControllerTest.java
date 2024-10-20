package com.emse.spring.Assignment.ControllerTest;

import com.emse.spring.Assignment.Controller.WindowController;
import com.emse.spring.Assignment.FakeEntityBuilder;
import com.emse.spring.Assignment.dao.RoomDao;
import com.emse.spring.Assignment.dao.WindowDao;
import com.emse.spring.Assignment.dto.WindowCommand;
import com.emse.spring.Assignment.model.Window;
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

@WebMvcTest(WindowController.class)
class WindowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WindowDao windowDao;

    @MockBean
    private RoomDao roomDao;

    @Test
    void shouldFindAll() throws Exception {
        Mockito.when(windowDao.findAll()).thenReturn(List.of(
                FakeEntityBuilder.createWindowEntity(1L, "Window 1", FakeEntityBuilder.createRoomEntity(1L, "Room 1")),
                FakeEntityBuilder.createWindowEntity(2L, "Window 2", FakeEntityBuilder.createRoomEntity(2L, "Room 2"))
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[*].name")
                        .value(Matchers.containsInAnyOrder("Window 1", "Window 2")));
    }

    @Test
    void shouldFindById() throws Exception {
        Window windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1", FakeEntityBuilder.createRoomEntity(1L, "Room 1"));
        Mockito.when(windowDao.findById("1")).thenReturn(Optional.of(windowEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Window 1"));
    }

    @Test
    void shouldReturnNullWhenFindByUnknownId() throws Exception {
        Mockito.when(windowDao.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/windows/999").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    void shouldCreate() throws Exception {
        Window windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1", FakeEntityBuilder.createRoomEntity(1L, "Room 1"));
        WindowCommand windowCommand = new WindowCommand("Window 1", windowEntity.getWindowStatus(), 1L);
        String json = objectMapper.writeValueAsString(windowCommand);

        Mockito.when(roomDao.getReferenceById(1L)).thenReturn(windowEntity.getRoom());
        Mockito.when(windowDao.save(Mockito.any(Window.class))).thenReturn(windowEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/windows")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Window 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void shouldUpdate() throws Exception {
        Window windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Window 1", FakeEntityBuilder.createRoomEntity(1L, "Room 1"));
        WindowCommand windowCommand = new WindowCommand("Updated Window", windowEntity.getWindowStatus(), 1L);
        String json = objectMapper.writeValueAsString(windowCommand);

        Mockito.when(windowDao.findById("1")).thenReturn(Optional.of(windowEntity));
        Mockito.when(roomDao.getReferenceById(1L)).thenReturn(windowEntity.getRoom());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/windows/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Window"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/windows/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
