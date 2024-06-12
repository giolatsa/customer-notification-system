package com.crocobet.customer.notification.system.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
