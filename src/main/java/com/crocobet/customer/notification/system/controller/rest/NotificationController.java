package com.crocobet.customer.notification.system.controller.rest;

import com.crocobet.customer.notification.system.model.Notification;
import com.crocobet.customer.notification.system.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification Tracking API", description = "API for tracking and querying notification statuses")
@SecurityRequirement(name = "basicAuth")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get notifications by customer ID", description = "Retrieve notifications for a specific customer.")
    public ResponseEntity<List<Notification>> getNotificationsByCustomerId(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long customerId) {
        return ResponseEntity.ok(notificationService.getNotificationsByCustomerId(customerId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Track a new notification", description = "Track a new notification for a customer.")
    public ResponseEntity<Void> trackNotification(
            @Parameter(description = "Notification to track", required = true) @RequestBody Notification notification) {
        notificationService.trackNotification(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
