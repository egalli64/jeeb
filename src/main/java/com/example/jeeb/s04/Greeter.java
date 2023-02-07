/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

/**
 * Interface for a simple service
 */
public interface Greeter {
    /**
     * Generate a greeting
     * 
     * @param name who has to be greeted
     * @return the greeting
     */
    String greetings(String name);
}
