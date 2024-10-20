package com.emse.spring.Assignment.Controller;

import com.emse.spring.Assignment.dao.SensorDao;
import com.emse.spring.Assignment.dto.SensorCommand;
import com.emse.spring.Assignment.dto.SensorDTO;
import com.emse.spring.Assignment.mapper.SensorMapper;
import com.emse.spring.Assignment.model.Sensor;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/sensors") // (2)
@Transactional // (3)
public class SensorController {
    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @GetMapping // (5)
    public List<SensorDTO> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .sorted(Comparator.comparing(SensorDTO::name))
                .collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public SensorDTO findById(@PathVariable Long id) {
        return sensorDao.findById(id).map(SensorMapper::of).orElse(null); // (7)
    }

    @PostMapping // (8)
    public ResponseEntity<SensorDTO> create(@RequestBody SensorCommand sensor) { // (9)
        Sensor entity = new Sensor(sensor.sensorType(), sensor.name());
        entity.setValue(sensor.value());
        Sensor saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved));
    }

    @PutMapping(path = "/{id}") // (10)
    public ResponseEntity<SensorDTO> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
        Sensor entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensor.value());
        entity.setName(sensor.name());
        entity.setSensorType(sensor.sensorType());
        // (11)
        return ResponseEntity.ok(SensorMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }
}
