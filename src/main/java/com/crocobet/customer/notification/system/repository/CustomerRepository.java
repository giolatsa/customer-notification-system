package com.crocobet.customer.notification.system.repository;

import com.crocobet.customer.notification.system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
