package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.CustomerDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {
  List<Customer> getAllCustomers();

  Customer getCustomerById(Long id);

  void addCustomer(Customer customer);

  void updateCustomer(Long id, Customer customer);

  void deleteCustomer(Long id);

  List<Customer> getCustomers(
      String name,
      String email,
      String phone,
      Boolean emailPref,
      Boolean smsPref,
      Boolean promotionalPref,
      String sort,
      String order);

  @Transactional
  void updateCustomersBatch(List<CustomerDTO> customers);
}
