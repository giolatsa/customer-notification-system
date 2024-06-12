package com.crocobet.customer.notification.system.controller.rest;

import com.crocobet.customer.notification.system.model.NotificationStatus;
import com.crocobet.customer.notification.system.model.NotificationType;
import com.crocobet.customer.notification.system.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reporting API", description = "API for generating reports on customer data and notification statistics")
@SecurityRequirement(name = "basicAuth")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/opt-in-counts")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get opt-in counts", description = "Retrieve the number of customers opted-in for different notification types.")
    public ResponseEntity<Map<NotificationType, Long>> getOptInCounts() {
        return ResponseEntity.ok(reportService.getOptInCounts());
    }

    @GetMapping("/notification-stats")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get notification statistics", description = "Retrieve statistics on notification delivery and failures.")
    public ResponseEntity<Map<String, Long>> getNotificationDeliveryStats() {
        return ResponseEntity.ok(reportService.getNotificationDeliveryStats());
    }

    @GetMapping("/notification-success-rates")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get notification success rates", description = "Retrieve the success rates of notifications by type and status.")
    public ResponseEntity<Map<NotificationType, Map<NotificationStatus, Long>>> getNotificationDeliverySuccessRates() {
        return ResponseEntity.ok(reportService.getNotificationDeliverySuccessRates());
    }
}
