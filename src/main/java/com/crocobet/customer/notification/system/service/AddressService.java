package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Address;
import com.crocobet.customer.notification.system.model.Customer;

import java.util.List;

public interface AddressService {
  List<Address> getAddressesByCustomer(Customer customer);

  Address saveAddress(Address address);

  void deleteAddress(Long id);
}
