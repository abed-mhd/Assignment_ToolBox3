package com.emse.spring.Assignment.dao;

import com.emse.spring.Assignment.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindowDao extends JpaRepository<Window, String>, WindowDaoCustom{
}
