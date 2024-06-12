package com.crocobet.customer.notification.system.repository;

import com.crocobet.customer.notification.system.model.Notification;
import com.crocobet.customer.notification.system.model.NotificationStatus;
import com.crocobet.customer.notification.system.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByCustomerId(Long customerId);
    Long countByStatus(NotificationStatus status);
    Long countByNotificationTypeAndStatus(NotificationType type, NotificationStatus status);
}
