package com.crocobet.customer.notification.system.controller.mvc;

import com.crocobet.customer.notification.system.model.*;
import com.crocobet.customer.notification.system.service.AddressService;
import com.crocobet.customer.notification.system.service.CustomerService;
import com.crocobet.customer.notification.system.service.PreferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final PreferenceService preferenceService;

    public CustomerController(CustomerService customerService, AddressService addressService, PreferenceService preferenceService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.preferenceService = preferenceService;
    }

    @GetMapping
    public String listCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Boolean emailPref,
            @RequestParam(required = false) Boolean smsPref,
            @RequestParam(required = false) Boolean promotionalPref,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            Model model) {
        List<Customer> customers = customerService.getCustomers(name, email, phone, emailPref, smsPref, promotionalPref, sort, order);
        model.addAttribute("customers", customers);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("emailPref", emailPref);
        model.addAttribute("smsPref", smsPref);
        model.addAttribute("promotionalPref", promotionalPref);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        return "customers";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/customers";
    }

    @GetMapping("/{id}/addresses")
    public String manageAddresses(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("addresses", customer.getAddresses());
        return "addresses";
    }

    @GetMapping("/{id}/addresses/new")
    public String showCreateAddressForm(@PathVariable Long id, Model model) {
        model.addAttribute("customerId", id);
        model.addAttribute("address", new Address());
        model.addAttribute("addressTypes", AddressType.values());

        return "address-form";
    }

    @PostMapping("/{id}/addresses")
    public String saveAddress(@PathVariable Long id, @ModelAttribute Address address) {
        Customer customer = customerService.getCustomerById(id);
        address.setCustomer(customer);
        addressService.saveAddress(address);
        return "redirect:/admin/customers/" + id + "/addresses";
    }

    @GetMapping("/{customerId}/addresses/delete/{addressId}")
    public String deleteAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return "redirect:/admin/customers/" + customerId + "/addresses";
    }

    @GetMapping("/{id}/preferences")
    public String managePreferences(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        List<Preference> preferences = customer.getPreferences();
        Map<String, Boolean> preferenceMap = new HashMap<>();
        for (NotificationType type : NotificationType.values()) {
            preferenceMap.put(type.name(), false);
        }
        for (Preference preference : preferences) {
            preferenceMap.put(preference.getNotificationType().name(), preference.isOptedIn());
        }
        model.addAttribute("customer", customer);
        model.addAttribute("preferences", preferenceMap);
        model.addAttribute("notificationTypes", NotificationType.values());
        return "preferences";
    }

    @PostMapping("/{id}/preferences/save")
    public String savePreferences(@PathVariable Long id, @RequestParam Map<String, String> allParams) {
        Customer customer = customerService.getCustomerById(id);

        // Load existing preferences
        List<Preference> existingPreferences = preferenceService.getPreferencesByCustomer(customer);

        // Create a map to easily access preferences by their notification type
        Map<NotificationType, Preference> preferenceMap = new HashMap<>();
        for (Preference preference : existingPreferences) {
            preferenceMap.put(preference.getNotificationType(), preference);
        }

        for (NotificationType type : NotificationType.values()) {
            String key = type.name();

            // Determine if the user has opted in for this notification type based on the form data
            boolean optedIn = allParams.containsKey(key) && allParams.get(key).equals("on");

            Preference preference = preferenceMap.get(type);
            if (preference == null) {
                preference = new Preference();
                preference.setCustomer(customer);
                preference.setNotificationType(type);
            }
            preference.setOptedIn(optedIn);
            preferenceService.savePreference(preference);
        }

        return "redirect:/admin/customers";
    }

}

