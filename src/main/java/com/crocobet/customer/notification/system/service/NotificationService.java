package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Notification;
import com.crocobet.customer.notification.system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        return notificationRepository.findByCustomerId(customerId);
    }

    public void trackNotification(Notification notification) {
        notificationRepository.save(notification);
    }

}
