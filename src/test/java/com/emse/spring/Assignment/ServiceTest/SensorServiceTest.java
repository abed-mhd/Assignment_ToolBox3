package com.emse.spring.Assignment.ServiceTest;

import com.emse.spring.Assignment.dto.SensorDTO;
import com.emse.spring.Assignment.model.SensorType;
import com.emse.spring.Assignment.service.SensorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.Collections;
import java.util.List;

@RestClientTest(SensorService.class) // (1)
class SensorServiceTest {
    @Autowired
    private SensorService sensorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockRestServiceServer server; // (2)

    @Test
    void shouldFindSensors() throws JsonProcessingException {
        // Arrange
        List<SensorDTO> expectedResponse = simulateApiResponse();
        String expectedUrl = "/sensors"; // Assuming this is the endpoint

        this.server
                .expect(MockRestRequestMatchers.requestTo(expectedUrl))
                .andRespond(
                        MockRestResponseCreators.withSuccess(
                                objectMapper.writeValueAsString(expectedResponse),
                                MediaType.APPLICATION_JSON
                        )
                );

        // Act
        List<SensorDTO> sensors = sensorService.findAllSensors();

        // Assert
        Assertions
                .assertThat(sensors)
                .hasSize(1)
                .extracting(SensorDTO::name)
                .contains("Temperature Sensor");
    }

    @Test
    void shouldFindSensorById() throws JsonProcessingException {
        // Arrange
        SensorDTO expectedSensor = new SensorDTO(1L, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);
        String expectedUrl = "/sensors/1"; // Assuming this is the endpoint

        this.server
                .expect(MockRestRequestMatchers.requestTo(expectedUrl))
                .andRespond(
                        MockRestResponseCreators.withSuccess(
                                objectMapper.writeValueAsString(expectedSensor),
                                MediaType.APPLICATION_JSON
                        )
                );

        // Act
        SensorDTO sensor = sensorService.findSensorById(1L);

        // Assert
        Assertions
                .assertThat(sensor)
                .isNotNull()
                .extracting(SensorDTO::name)
                .isEqualTo("Temperature Sensor");
    }

    private List<SensorDTO> simulateApiResponse() {
        SensorDTO sensor = new SensorDTO(1L, "Temperature Sensor", 25.0, SensorType.TEMPERATURE);
        return Collections.singletonList(sensor);
    }
}