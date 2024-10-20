package com.emse.spring.Assignment.DaoTest;

import com.emse.spring.Assignment.dao.RoomDao;
import com.emse.spring.Assignment.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindARoomById() {
        Room room = roomDao.getReferenceById(-10L); // Assuming room with ID -10 exists
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
        Assertions.assertThat(room.getFloor()).isEqualTo(2);
        Assertions.assertThat(room.getTargetTemperature()).isEqualTo(18.0);
    }
}