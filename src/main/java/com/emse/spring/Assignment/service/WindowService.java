package com.emse.spring.Assignment.service;

import com.emse.spring.Assignment.dto.WindowCommand;
import com.emse.spring.Assignment.dto.WindowDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class WindowService {

    private final RestTemplate restTemplate;

    public WindowService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }

    public WindowDTO findWindowById(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/windows/{id}")
                .build(id)
                .toString();
        return restTemplate.getForObject(uri, WindowDTO.class);
    }

    public List<WindowDTO> findAllWindows() {
        String uri = UriComponentsBuilder.fromUriString("/windows")
                .build()
                .toString();
        return Arrays.asList(restTemplate.getForObject(uri, WindowDTO[].class));
    }

    public WindowDTO createWindow(WindowCommand window) {
        String uri = UriComponentsBuilder.fromUriString("/windows")
                .build()
                .toString();
        return restTemplate.postForObject(uri, window, WindowDTO.class);
    }

    public void deleteWindow(Long id) {
        String uri = UriComponentsBuilder.fromUriString("/windows/{id}")
                .build(id)
                .toString();
        restTemplate.delete(uri);
    }
}
