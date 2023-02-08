/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s07;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * JPA entity
 */
@Entity
public class Region {
    @Id
    @Column(name = "REGION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    /**
     * No-argument constructor
     */
    public Region() {
    }

    /**
     * Being the entity with GeneratedValue, the id should not be set
     * 
     * @param name the region name
     */
    public Region(String name) {
        this.name = name;
    }

    /**
     * Getter
     * 
     * @return the region id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter
     * 
     * @param id the region id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter
     * 
     * @return the region name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter
     * 
     * @return the region name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }
}
