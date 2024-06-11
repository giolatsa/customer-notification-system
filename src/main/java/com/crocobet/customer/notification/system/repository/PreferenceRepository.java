package com.crocobet.customer.notification.system.repository;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    List<Preference> findByCustomer(Customer customer);

}