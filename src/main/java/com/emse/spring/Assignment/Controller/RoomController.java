package com.emse.spring.Assignment.Controller;

import com.emse.spring.Assignment.dao.RoomDao;
import com.emse.spring.Assignment.dto.RoomCommand;
import com.emse.spring.Assignment.dto.RoomDTO;
import com.emse.spring.Assignment.mapper.RoomMapper;
import com.emse.spring.Assignment.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDTO> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDTO findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomMapper::of).orElse(null);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> create(@RequestBody RoomCommand room) {
        Room entity = new Room(room.floor(), room.name(), room.currentTemperature_id(), room.target_temperature());
        Room saved = roomDao.save(entity);
        return ResponseEntity.ok(RoomMapper.of(saved));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable Long id, @RequestBody RoomCommand room) {
        Room entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setName(room.name());
        entity.setFloor(room.floor());
        entity.setCurrentTemperature(room.currentTemperature_id());
        entity.setTargetTemperature(room.target_temperature());
        return ResponseEntity.ok(RoomMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }
}
