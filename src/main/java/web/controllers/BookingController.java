package web.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import beans.models.Ticket;
import beans.services.BookingService;
import beans.services.EventService;
import beans.services.TicketService;
import beans.services.UserService;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Qualifier("bookingServiceImpl")
    @Autowired
    private BookingService bookingService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Qualifier("eventServiceImpl")
    @Autowired
    private EventService eventService;

    @GetMapping("/tickets")
    public String getTickets(@RequestParam("eventName") String eventName,
                             @RequestParam("time") LocalDateTime time,
                             @RequestParam("auditorium") String auditorium,
                             Model model) {
        List<Integer> seats = bookingService.getFreeSeats(eventName, auditorium, time);
        model.addAttribute("seats", seats);
        model.addAttribute("eventName", eventName);
        model.addAttribute("time", time);
        model.addAttribute("auditorium", auditorium);
        return "tickets";
    }

    @PostMapping("/tickets/book")
    public ModelAndView bookTickets(@RequestParam("eventName") String eventName,
                                    @RequestParam("time") LocalDateTime time,
                                    @RequestParam("selectedSeats") List<Integer> selectedSeats,
                                    @RequestParam("auditorium") String auditorium) {
        Ticket ticket = ticketService.createTicket(
                eventService.getByName(eventName).get(0),
                time,
                selectedSeats,
                userService.getById(9999),
                100.0
                                                  );
        bookingService.bookTicket(userService.getById(9999), ticket);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("eventName", eventName);
        attributes.put("time", time.toString());
        attributes.put("auditorium", auditorium);
        return new ModelAndView("redirect:/tickets", attributes);
    }

}
