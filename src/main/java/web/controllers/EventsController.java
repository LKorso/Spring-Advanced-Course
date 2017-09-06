package web.controllers;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {

    private static final String ALL_EVENTS_PAGE = "all-events";

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @GetMapping
    public String getEvents(Model model) {
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return ALL_EVENTS_PAGE;
    }
}
