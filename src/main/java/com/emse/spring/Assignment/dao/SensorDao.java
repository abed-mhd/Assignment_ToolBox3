package com.emse.spring.Assignment.dao;

import com.emse.spring.Assignment.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorDao extends JpaRepository<Sensor, Long> {


    @Query("select c from Sensor c where c.name=:name")
    Sensor findByName(@Param("name") String name);

    @Modifying // (3)
    @Query("delete from Sensor c where c.name = ?1")
    void deleteByName(String name);
}
