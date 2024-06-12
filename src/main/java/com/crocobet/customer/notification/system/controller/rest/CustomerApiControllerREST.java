package com.crocobet.customer.notification.system.controller.rest;

import com.crocobet.customer.notification.system.model.Address;
import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.CustomerDTO;
import com.crocobet.customer.notification.system.model.Preference;
import com.crocobet.customer.notification.system.service.AddressService;
import com.crocobet.customer.notification.system.service.CustomerService;
import com.crocobet.customer.notification.system.service.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Management API", description = "API for managing customers, addresses, and preferences")
public class CustomerApiControllerREST {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final PreferenceService preferenceService;

    public CustomerApiControllerREST(CustomerService customerService, AddressService addressService, PreferenceService preferenceService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.preferenceService = preferenceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all customers", description = "Retrieve all customers along with their addresses and preferences.")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/addresses")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get customer addresses", description = "Retrieve addresses for a specific customer.")
    public ResponseEntity<List<Address>> getCustomerAddresses(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(addressService.getAddressesByCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/{id}/preferences")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get customer preferences", description = "Retrieve preferences for a specific customer.")
    public ResponseEntity<List<Preference>> getCustomerPreferences(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(preferenceService.getPreferencesByCustomer(customer), HttpStatus.OK);
    }


    @PutMapping("/batch/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update customes in batch", description = "Update multiple customers in batch.")
    public ResponseEntity<String> updateCustomers(@RequestBody List<CustomerDTO> customers) {
        customerService.updateCustomersBatch(customers);
        return new ResponseEntity<>("Customers updated successfully", HttpStatus.OK);
    }
}
