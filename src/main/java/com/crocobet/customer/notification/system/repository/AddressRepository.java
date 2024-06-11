package com.crocobet.customer.notification.system.repository;

import com.crocobet.customer.notification.system.model.Address;
import com.crocobet.customer.notification.system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCustomer(Customer customer);
}
