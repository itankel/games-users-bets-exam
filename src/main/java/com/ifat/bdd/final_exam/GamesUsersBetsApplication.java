package com.ifat.bdd.final_exam;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GamesUsersBetsApplication {

    public static void main(String[] args) {
//        Logger.getLogger("org").setLevel(Level.OFF);
//        Logger.getLogger("akka").setLevel(Level.OFF);

        ConfigurableApplicationContext context = SpringApplication.run(GamesUsersBetsApplication.class, args);
        System.out.println();

    }

}
