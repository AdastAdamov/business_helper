package com.adast_adamov.business_helper.controllers;

import com.adast_adamov.business_helper.exceptions.ExpiredTokenException;
import com.adast_adamov.business_helper.models.Day;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.models.Week;
import com.adast_adamov.business_helper.services.interfaces.EventService;
import com.adast_adamov.business_helper.services.interfaces.ProductService;
import com.adast_adamov.business_helper.services.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getLoginPage(@CookieValue(value = "token") String token,
                               Model model) {
        if (token != null) {
            return getMainPage(token, model);
        }
        else {
            return "login_page";
        }
    }

    @GetMapping("/dashboard")
    public String getMainPage(@CookieValue(value = "token") String token,
                              Model model) {
        try {
            User user = tokenService.getUser(token);

            return "main_page";
        } catch (ExpiredTokenException e) {
            e.printStackTrace();
            return getLoginPage(null, model);
        }
    }

    @GetMapping("/main_page_cards")
    public String getCardPage(@CookieValue(value = "token") String token,
                              Model model) {
        return "main_page_cards";
    }

    @GetMapping("/main_page_add_card")
    public String getAddCardPage(@CookieValue(value = "token") String token,
                              Model model) {
        model.addAttribute("user_products", productService.getBanks());
        return "main_page_add_card";
    }

    @GetMapping("/main_page_calendar")
    public String getCalendarPage(@CookieValue(value = "token") String token,
                                  Model model) {
        LocalDate currentDate = LocalDate.now();
        List<Week> calendar = eventService.getWeeks(currentDate.getYear(), currentDate.getMonthValue());

        model.addAttribute("calendar", calendar);
        return "main_page_calendar";
    }
}
