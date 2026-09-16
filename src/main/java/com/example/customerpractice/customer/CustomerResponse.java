package com.example.customerpractice.customer;

import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}