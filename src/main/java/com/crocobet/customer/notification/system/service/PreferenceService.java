package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.Preference;
import com.crocobet.customer.notification.system.repository.PreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public List<Preference> getPreferencesByCustomer(Customer customer) {
        return preferenceRepository.findByCustomer(customer);
    }

    public Preference savePreference(Preference preference) {
        return preferenceRepository.save(preference);
    }

}