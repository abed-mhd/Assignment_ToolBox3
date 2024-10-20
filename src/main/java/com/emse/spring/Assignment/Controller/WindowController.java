package com.emse.spring.Assignment.Controller;

import com.emse.spring.Assignment.dao.RoomDao;
import com.emse.spring.Assignment.dao.WindowDao;
import com.emse.spring.Assignment.dto.WindowCommand;
import com.emse.spring.Assignment.dto.WindowDTO;
import com.emse.spring.Assignment.mapper.WindowMapper;
import com.emse.spring.Assignment.model.Window;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {
    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<WindowDTO> findAll() {
        return windowDao.findAll()
                .stream()
                .map(WindowMapper::of)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public WindowDTO findById(@PathVariable Long id) {
        return windowDao.findById(String.valueOf(id)).map(WindowMapper::of).orElse(null);
    }

    @PostMapping
    public ResponseEntity<WindowDTO> create(@RequestBody WindowCommand window) {
        Window entity = new Window(window.name(), window.windowStatus(), roomDao.getReferenceById(window.roomId()));
        Window saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowMapper.of(saved));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<WindowDTO> update(@PathVariable Long id, @RequestBody WindowCommand window) {
        Window entity = windowDao.findById(String.valueOf(id)).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setName(window.name());
        entity.setWindowStatus(window.windowStatus());
        entity.setRoom(roomDao.getReferenceById(window.roomId()));
        return ResponseEntity.ok(WindowMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(String.valueOf(id));
    }
}
