/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

/**
 * Interface for a simple service
 */
public interface Adder {
    /**
     * Add two integers
     * 
     * @param left  first operand
     * @param right second operand
     * @return their sum
     */
    int add(int left, int right);
}
