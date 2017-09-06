package web.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beans.services.BookingService;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;

    @GetMapping("/ticket")
    public String getTickets(@RequestParam("eventName") String eventName,
                             @RequestParam("time") LocalDateTime time,
                             @RequestParam("auditorium") String auditorium,
                             Model model) {
        String h = null;
        return null;
    }

}
