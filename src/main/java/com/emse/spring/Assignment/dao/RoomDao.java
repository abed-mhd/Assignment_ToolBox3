package com.emse.spring.Assignment.dao;

import com.emse.spring.Assignment.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomDao extends JpaRepository<Room, Long> {


    @Query("select c from Room c where c.name=:name")
    Room findByName(@Param("name") String name);

    @Modifying // (3)
    @Query("delete from Room c where c.name = ?1")
    void deleteByName(String name);
}
