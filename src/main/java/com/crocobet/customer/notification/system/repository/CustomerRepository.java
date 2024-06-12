package com.crocobet.customer.notification.system.repository;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Long countByPreferencesNotificationTypeAndPreferencesOpted(NotificationType type, boolean optedIn);
}
