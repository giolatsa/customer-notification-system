package com.crocobet.customer.notification.system.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
