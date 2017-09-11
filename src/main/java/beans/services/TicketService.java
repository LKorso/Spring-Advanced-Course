package beans.services;

import java.time.LocalDateTime;
import java.util.List;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;

public interface TicketService {

    Ticket createTicket(Event event, LocalDateTime dateTime, List<Integer> seats, User user, double price);
}
