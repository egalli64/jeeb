/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s07;

import java.util.Optional;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * SLSB acting as JPA repository
 */
@Stateless
public class RegionRepo {
    @PersistenceContext
    private EntityManager em;

    /**
     * Access the entity manger
     * 
     * @param id the region id
     * @return the associated region
     */
    public Optional<Region> get(Integer id) {
        return Optional.ofNullable(em.find(Region.class, id));
    }
}
