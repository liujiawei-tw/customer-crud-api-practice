package com.example.customerpractice.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CustomerControllerIntegrationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomerReturnsCreated() {
        Customer customer = customerService.create(
                "Alice",
                "alice.integration@example.com",
                "0912345678"
        );

        assertThat(customer.getName()).isEqualTo("Alice");
        assertThat(customer.getEmail()).isEqualTo("alice.integration@example.com");
    }
}