package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.Preference;

import java.util.List;

public interface PreferenceService {
  List<Preference> getPreferencesByCustomer(Customer customer);

  Preference savePreference(Preference preference);
}
