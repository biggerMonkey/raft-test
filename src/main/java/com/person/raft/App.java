package com.person.raft;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(false);
        for (String cmd : args) {
            System.out.println("cmd:" + cmd);
        }
        System.out.println("Hello world!");
    }
}
