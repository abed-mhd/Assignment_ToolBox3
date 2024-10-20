package com.emse.spring.Assignment.dao;

import com.emse.spring.Assignment.model.Window;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findRoomsWithOpenWindows(Long id);
}
