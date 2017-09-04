package beans.configuration;

import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
public class EventConfiguration {

    @Value("#{auditoriumList}")
    private List<Auditorium> auditoriums;

    @Bean
    public Event eventOne() {
        return new Event(1, "Event One", Rate.LOW, 250.50,
                LocalDateTime.of(2017, 9, 20, 14, 10), auditoriums.get(0));
    }

    @Bean
    public Event eventTwo() {
        return new Event(2, "Event Two", Rate.HIGH, 499.99,
                LocalDateTime.of(2017, 10, 10, 20, 10), auditoriums.get(1));
    }

    @Bean
    public List<Event> basicEvents() {
        return Arrays.asList(eventOne(), eventTwo());
    }

}
