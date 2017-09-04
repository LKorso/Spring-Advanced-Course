package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class IndexController {

    private static final String INDEX_PAGE_NAME = "index";

    @GetMapping
    public String index(Model model) {
        model.addAttribute("attribute", "This attribute was added in controller method!");
        return INDEX_PAGE_NAME;
    }
}
