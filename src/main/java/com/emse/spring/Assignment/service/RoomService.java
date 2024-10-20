package com.emse.spring.Assignment.service;

import com.emse.spring.Assignment.dto.RoomCommand;
import com.emse.spring.Assignment.dto.RoomDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomService {

    private final RestTemplate restTemplate;

    public RoomService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }

    public RoomDTO findRoomById(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/rooms/{id}")
                .build(id)
                .toString();
        return restTemplate.getForObject(uri, RoomDTO.class);
    }

    public List<RoomDTO> findAllRooms() {
        String uri = UriComponentsBuilder.fromUriString("/rooms")
                .build()
                .toString();
        return Arrays.asList(restTemplate.getForObject(uri, RoomDTO[].class));
    }

    public RoomDTO createRoom(RoomCommand room) {
        String uri = UriComponentsBuilder.fromUriString("/rooms")
                .build()
                .toString();
        return restTemplate.postForObject(uri, room, RoomDTO.class);
    }

    public void deleteRoom(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/rooms/{id}")
                .build(id)
                .toString();
        restTemplate.delete(uri);
    }
}
