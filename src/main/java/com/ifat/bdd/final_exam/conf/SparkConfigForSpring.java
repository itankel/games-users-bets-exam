package com.ifat.bdd.final_exam.conf;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 
 */

@Configuration
public class SparkConfigForSpring {
    {
        System.setProperty("hadoop.home.dir", "C:\\Hadoop\\winutils\\");

    }

    @Bean
    public SparkSession sparkSessionDev(){
        return SparkSession.builder().master("local[*]").appName("games-users-bets").getOrCreate();
    }

}



