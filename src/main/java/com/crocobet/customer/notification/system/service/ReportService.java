package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.NotificationStatus;
import com.crocobet.customer.notification.system.model.NotificationType;
import com.crocobet.customer.notification.system.repository.CustomerRepository;
import com.crocobet.customer.notification.system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    private final CustomerRepository customerRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public ReportService(CustomerRepository customerRepository, NotificationRepository notificationRepository) {
        this.customerRepository = customerRepository;
        this.notificationRepository = notificationRepository;
    }

    public Map<NotificationType, Long> getOptInCounts() {
        Map<NotificationType, Long> optInCounts = new HashMap<>();
        for (NotificationType type : NotificationType.values()) {
            Long count = customerRepository.countByPreferencesNotificationTypeAndPreferencesOpted(type, true);
            optInCounts.put(type, count);
        }
        return optInCounts;
    }

    public Map<String, Long> getNotificationDeliveryStats() {
        Map<String, Long> deliveryStats = new HashMap<>();
        deliveryStats.put("successfulDeliveries", notificationRepository.countByStatus(NotificationStatus.DELIVERED));
        deliveryStats.put("failedDeliveries", notificationRepository.countByStatus(NotificationStatus.FAILED));
        return deliveryStats;
    }

    public Map<NotificationType, Map<NotificationStatus, Long>> getNotificationDeliverySuccessRates() {
        Map<NotificationType, Map<NotificationStatus, Long>> successRates = new HashMap<>();
        for (NotificationType type : NotificationType.values()) {
            Map<NotificationStatus, Long> statusCounts = new HashMap<>();
            for (NotificationStatus status : NotificationStatus.values()) {
                Long count = notificationRepository.countByNotificationTypeAndStatus(type, status);
                statusCounts.put(status, count);
            }
            successRates.put(type, statusCounts);
        }
        return successRates;
    }
}
