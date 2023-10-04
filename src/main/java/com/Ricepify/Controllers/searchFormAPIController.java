package com.Ricepify.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class searchFormAPIController {
    @GetMapping("/search")
    public String showSearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String performSearch(@RequestParam("searchOption") String selectedOption) {

        String apiQuery = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + selectedOption;


        return "redirect:/results?q=" + selectedOption;
    }
}

