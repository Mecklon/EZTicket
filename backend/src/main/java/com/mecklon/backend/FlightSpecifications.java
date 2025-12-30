package com.mecklon.backend;


import com.mecklon.backend.model.Flight;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightSpecifications {

    public static Specification<Flight> fromPlace(String from) {
        return (root, query, cb) -> cb.equal(root.get("from"), from);
    }

    public static Specification<Flight> toPlace(String to) {
        return (root, query, cb) -> cb.equal(root.get("to"), to);
    }

    public static Specification<Flight> airline(String airlineName) {
        return (root, query, cb) -> (airlineName == null || airlineName.isEmpty())
                ? null
                : cb.equal(root.get("airlineName"), airlineName);
    }

    public static Specification<Flight> departureOn(LocalDate date) {
        return (root, query, cb) -> {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(23, 59, 59);
            return cb.between(root.get("departure"), startOfDay, endOfDay);
        };
    }
}