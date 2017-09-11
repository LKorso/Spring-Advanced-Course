package beans.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import beans.daos.BookingDAO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;

@Service
public class TicketServiceImpl implements TicketService {

    @Qualifier("inMemoryBookingDAO")
    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public Ticket createTicket(Event event, LocalDateTime dateTime, List<Integer> seats, User user, double price) {
        Ticket newTicket = new Ticket(event, dateTime, seats, user, price);
        bookingDAO.create(user, newTicket);
        return newTicket;
    }
}
