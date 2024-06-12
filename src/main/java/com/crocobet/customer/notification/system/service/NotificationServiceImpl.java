package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Notification;
import com.crocobet.customer.notification.system.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        return notificationRepository.findByCustomerId(customerId);
    }

    @Override
    public void trackNotification(Notification notification) {
        notificationRepository.save(notification);
    }

}
