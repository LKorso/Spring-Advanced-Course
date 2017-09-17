package web.controllers;

import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/events")
public class EventsController {

    private static final String ALL_EVENTS_PAGE = "all-events";

    @Autowired
    @Qualifier("eventServiceImpl")
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public String getEvents(Model model) {
        List<Event> events = eventService.getAll();
        model.addAttribute("events", events);
        return ALL_EVENTS_PAGE;
    }

    @PostMapping("/load")
    public String loadEvents(@RequestParam("file") MultipartFile file) throws IOException {
        String extension = getFileExtension(file.getOriginalFilename());
        File temp = File.createTempFile("temp", extension);
        List<Event> events = objectMapper.readValue(temp, new TypeReference<List<Event>>(){});
        events.forEach(eventService::create);
        return "redirect:/mvc/events";
    }

    private String getFileExtension(String originalFilename) {
        int dotPosition = originalFilename.indexOf('.');
        return originalFilename.substring(dotPosition);
    }
}
