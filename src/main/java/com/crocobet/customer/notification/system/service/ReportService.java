package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.NotificationStatus;
import com.crocobet.customer.notification.system.model.NotificationType;

import java.util.Map;

public interface ReportService {
  Map<NotificationType, Long> getOptInCounts();

  Map<String, Long> getNotificationDeliveryStats();

  Map<NotificationType, Map<NotificationStatus, Long>> getNotificationDeliverySuccessRates();
}
