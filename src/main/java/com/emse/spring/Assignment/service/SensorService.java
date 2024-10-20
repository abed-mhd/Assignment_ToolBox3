package com.emse.spring.Assignment.service;

import com.emse.spring.Assignment.dto.SensorCommand;
import com.emse.spring.Assignment.dto.SensorDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class SensorService {

    private final RestTemplate restTemplate;

    public SensorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }

    public SensorDTO findSensorById(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/sensors/{id}")
                .build(id)
                .toString();
        return restTemplate.getForObject(uri, SensorDTO.class);
    }

    public List<SensorDTO> findAllSensors() {
        String uri = UriComponentsBuilder.fromUriString("/sensors")
                .build()
                .toString();
        return Arrays.asList(restTemplate.getForObject(uri, SensorDTO[].class));
    }

    public SensorDTO createSensor(SensorCommand sensor) {
        String uri = UriComponentsBuilder.fromUriString("/sensors")
                .build()
                .toString();
        return restTemplate.postForObject(uri, sensor, SensorDTO.class);
    }

    public void deleteSensor(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/sensors/{id}")
                .build(id)
                .toString();
        restTemplate.delete(uri);
    }
}