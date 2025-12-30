package com.mecklon.backend;

import com.mecklon.backend.model.Flight;
import com.mecklon.backend.model.Seat;
import com.mecklon.backend.model.key.SeatKey;
import com.mecklon.backend.model.type.SeatStatus;
import com.mecklon.backend.repo.FlightRepository;
import com.mecklon.backend.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
@RequiredArgsConstructor
public class FlightDataLoader implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    @Override
    public void run(String... args) throws Exception {

        if(flightRepository.count()>0)return;

        List<String> cities = List.of("Bangalore", "Mumbai", "Delhi", "Hyderabad", "Chennai");
        List<String> airlines = List.of("Air India", "Spice jet", "Indigo", "King Fisher", "Spirit");

        List<Flight> flights = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 5000; i++) {
            String from = cities.get(random.nextInt(cities.size()));
            String to = cities.get(random.nextInt(cities.size()));

            while (from.equals(to)) {
                to = cities.get(random.nextInt(cities.size()));
            }

            String airline = airlines.get(random.nextInt(airlines.size()));

            LocalDateTime departure = LocalDateTime.now();
            departure = departure.plusDays(random.nextInt(5));

            LocalDateTime arrival = departure.plusHours(1 + random.nextInt(7));

            int price = random.nextInt(600, 4000);

            Flight flight = Flight.builder()
                    .from(from)
                    .to(to)
                    .departure(departure)
                    .arrival(arrival)
                    .airlineName(airline)
                    .price(price)
                    .build();

            flights.add(flight);
        }

        flightRepository.saveAll(flights);

        Flight flight = flightRepository.findById((long)1).orElse(null);

        List<Seat> seats = new ArrayList<>();

        for(char c = 'a'; c<='g';c++){
            for(int i = 1;i< 33;i++){
                Seat seat = new Seat(
                        new SeatKey((long)1, c, i),
                        flight,
                        SeatStatus.AVAILABLE,
                        null,
                        null,
                        null
                );
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);

    }
}
