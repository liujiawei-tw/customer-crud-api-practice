package com.example.customerpractice.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String email, String phone) {
        if (customerRepository.existsByEmail(email)) {
            throw new DuplicateCustomerEmailException(email);
        }
        Customer customer = new Customer(name, email, phone);
        return customerRepository.save(customer);
    }

    private Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return getCustomer(id);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer update(Long id, String name, String email, String phone) {
        Customer customer = getCustomer(id);
        if (customerRepository.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateCustomerEmailException(email);
        }
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        return customerRepository.save(customer);
    }

    public void delete(Long id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }
}
