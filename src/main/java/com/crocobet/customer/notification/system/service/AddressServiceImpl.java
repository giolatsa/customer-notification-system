package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Address;
import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddressesByCustomer(Customer customer) {
        return addressRepository.findByCustomer(customer);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}