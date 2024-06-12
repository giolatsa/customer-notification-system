package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.Preference;
import com.crocobet.customer.notification.system.repository.PreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public List<Preference> getPreferencesByCustomer(Customer customer) {
        return preferenceRepository.findByCustomer(customer);
    }

    @Override
    public Preference savePreference(Preference preference) {
        return preferenceRepository.save(preference);
    }

}