package com.example.customerpractice.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {
        Customer customer = customerService.create(
                request.name(),
                request.email(),
                request.phone());

        return CustomerResponse.from(customer);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll()
                .stream()
                .map(CustomerResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return CustomerResponse.from(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
    ) {
        Customer customer = customerService.update(
               id,
               request.name(),
               request.email(),
               request.phone()
        );
        return CustomerResponse.from(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
