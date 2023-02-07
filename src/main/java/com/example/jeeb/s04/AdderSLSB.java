/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

/**
 * A Remote Stateless Session Bean
 * 
 * The interface passed to the remote annotation is used to generate the access stub
 */
@Stateless
@Remote(Adder.class)
public class AdderSLSB implements Adder {
    @Override
    public int add(int left, int right) {
        return left + right;
    }
}
