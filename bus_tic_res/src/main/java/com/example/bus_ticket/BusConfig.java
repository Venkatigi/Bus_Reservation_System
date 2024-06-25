package com.example.bus_ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.JULY;

@Configuration
public class BusConfig {

    @Bean
    CommandLineRunner commandLineRunner(BusRepository busRepository) {
        return args -> {
            // Creating two instances of Bus
            Bus cd = new Bus(
                    100,
                    "Chennai",
                    "Delhi",
                    "AC",
                    "Sleeper",
                    10000,
                    LocalDate.of(2024, JULY, 5)
            );
            //cd.setId(1L);


            Bus cb = new Bus(
                    101,
                    "Chennai",
                    "Bengaluru",
                    "AC",
                    "Sleeper",
                    5000,
                    LocalDate.of(2024, JULY, 5)
            );

            // Saving the instances to the database using BusRepository
            busRepository.saveAll(
                    List.of(cd, cb)
            );
        };
    }
}