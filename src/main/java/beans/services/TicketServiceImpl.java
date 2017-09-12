package beans.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public Ticket createTicket(Event event, LocalDateTime dateTime, List<Integer> seats, User user, double price) {
        return new Ticket(event, dateTime, seats, user, price);
    }
}
