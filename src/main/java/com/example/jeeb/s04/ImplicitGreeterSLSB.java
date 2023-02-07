/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

import jakarta.ejb.Stateless;

/**
 * An implicitly Local Stateless Session Bean
 * 
 * No interface required for locals
 */
@Stateless
public class ImplicitGreeterSLSB {
    /**
     * Generate a greeting
     * 
     * @param name who has to be greeted
     * @return the greeting
     */
    public String greetings(String name) {
        return "Hi, " + name;
    }
}
