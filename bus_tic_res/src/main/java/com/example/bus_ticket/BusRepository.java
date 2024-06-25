package com.example.bus_ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
    List<Bus> findBySourceAndDestinationAndDate(String source, String destination, LocalDate date);
}