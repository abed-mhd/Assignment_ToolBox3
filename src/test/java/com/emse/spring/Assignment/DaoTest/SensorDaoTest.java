package com.emse.spring.Assignment.DaoTest;

import com.emse.spring.Assignment.dao.SensorDao;
import com.emse.spring.Assignment.model.Sensor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class SensorDaoTest {

    @Autowired
    private SensorDao sensorDao;

    @Test
    public void shouldFindASensorById() {
        Sensor sensor = sensorDao.getReferenceById(-10L);
        Assertions.assertThat(sensor.getName()).isEqualTo("Temperature room 2");
        Assertions.assertThat(sensor.getValue()).isEqualTo(21.3);
        Assertions.assertThat(sensor.getSensorType().name()).isEqualTo("TEMPERATURE");
    }
}