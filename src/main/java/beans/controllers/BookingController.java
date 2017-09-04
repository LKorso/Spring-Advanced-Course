package beans.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import beans.services.BookingService;

@Controller("/booking")
public class BookingController {

    @Qualifier("bookingServiceImpl")
    private BookingService bookingService;


}
