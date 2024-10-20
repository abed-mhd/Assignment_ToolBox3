package com.emse.spring.Assignment.dao;

import com.emse.spring.Assignment.model.Window;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomsWithOpenWindows(Long id) {
        String jpql = "select w from Window w inner join w.windowStatus s " +
                "where w.room.id = :id and s.value > 0.0 order by w.name";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getResultList();
    }
}