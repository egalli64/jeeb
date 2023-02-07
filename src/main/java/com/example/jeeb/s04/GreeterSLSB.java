/*
 * Introduction to Jakarta Enterprise Edition - Enterprise Bean
 * 
 * https://github.com/egalli64/jeeb
 */
package com.example.jeeb.s04;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;

/**
 * An explicitly Local Stateless Session Bean
 * 
 * The interface could be stated in the Local annotation or in the more common way
 */
@Stateless
@Local
public class GreeterSLSB implements Greeter {
    public String greetings(String name) {
        return "Hello, " + name;
    }
}
