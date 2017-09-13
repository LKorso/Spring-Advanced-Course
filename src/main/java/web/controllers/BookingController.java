package web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        List<Ticket> tickets = bookingService.getTicketsForEvent(eventName, auditorium, time);
        model.addAttribute("tickets", tickets);
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
        return new ModelAndView("redirect:/mvc/booking/tickets", attributes);
    }

    @PostMapping("/tickets/print/pdf")
    @ResponseBody
    public void generatePdf(@RequestParam("eventName") String eventName,
                     @RequestParam("time") LocalDateTime time,
                     @RequestParam("auditorium") String auditorium,
                     HttpServletResponse response) {
        List<Ticket> tickets = bookingService.getTicketsForEvent(eventName, auditorium, time);
        String filePath = bookingService.createTicketsPdf(tickets);

        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
