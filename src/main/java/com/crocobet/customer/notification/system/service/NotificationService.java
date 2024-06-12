package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Notification;

import java.util.List;

public interface NotificationService {
  List<Notification> getNotificationsByCustomerId(Long customerId);

  void trackNotification(Notification notification);
}
