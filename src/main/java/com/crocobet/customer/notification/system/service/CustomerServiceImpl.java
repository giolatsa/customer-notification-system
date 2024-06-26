package com.crocobet.customer.notification.system.service;

import com.crocobet.customer.notification.system.model.Customer;
import com.crocobet.customer.notification.system.model.CustomerDTO;
import com.crocobet.customer.notification.system.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            customerRepository.save(existingCustomer);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers(String name, String email, String phone, Boolean emailPref, Boolean smsPref, Boolean promotionalPref, String sort, String order) {
        StringBuilder queryBuilder = new StringBuilder("SELECT c FROM Customer c LEFT JOIN FETCH c.preferences p WHERE 1=1");

        if (name != null && !name.isEmpty()) {
            queryBuilder.append(" AND LOWER(c.name) LIKE LOWER('%").append(name).append("%')");
        }
        if (email != null && !email.isEmpty()) {
            queryBuilder.append(" AND LOWER(c.email) LIKE LOWER('%").append(email).append("%')");
        }
        if (phone != null && !phone.isEmpty()) {
            queryBuilder.append(" AND LOWER(c.phone) LIKE LOWER('%").append(phone).append("%')");
        }
        if (emailPref != null && emailPref) {
            queryBuilder.append(" AND EXISTS (SELECT p FROM Preference p WHERE p.customer = c AND p.notificationType = NotificationType.EMAIL AND p.opted = TRUE)");
        }
        if (smsPref != null && smsPref) {
            queryBuilder.append(" AND EXISTS (SELECT p FROM Preference p WHERE p.customer = c AND p.notificationType = NotificationType.SMS AND p.opted = TRUE)");
        }
        if (promotionalPref != null && promotionalPref) {
            queryBuilder.append(" AND EXISTS (SELECT p FROM Preference p WHERE p.customer = c AND p.notificationType = NotificationType.PROMOTIONAL AND p.opted = TRUE)");
        }

        if (sort != null && !sort.isEmpty()) {
            queryBuilder.append(" ORDER BY c.").append(sort);
            if (order != null && order.equalsIgnoreCase("desc")) {
                queryBuilder.append(" DESC");
            } else {
                queryBuilder.append(" ASC");
            }
        }

        return findCustomersWithCriteria(queryBuilder.toString());
    }

    @Override
    @Transactional
    public void updateCustomersBatch(List<CustomerDTO> customers) {
        List<Customer> customerList=customers.stream()
                .map(customerDTO -> Customer.builder()
                        .id(customerDTO.getId())
                        .name(customerDTO.getName())
                        .email(customerDTO.getEmail())
                        .phone(customerDTO.getPhone())
                        .build())
                .toList();

        customerRepository.saveAll(customerList);
    }


    private List<Customer> findCustomersWithCriteria(String query) {
        Query q = entityManager.createQuery(query, Customer.class);
        return q.getResultList();
    }
}
