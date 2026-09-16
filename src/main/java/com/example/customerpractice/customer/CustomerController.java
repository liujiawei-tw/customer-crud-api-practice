package com.example.customerpractice.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("api/v1/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {

        Customer customer= customerService.create(
                request.name(),
                request.email(),
                request.phone());

        return CustomerResponse.from(customer);
    }

    @GetMapping("api/v1/customers")
    public List<CustomerResponse> findAll() {
        return customerService.findAll()
                .stream()
                .map(CustomerResponse::from)
                .toList();
    }

    @GetMapping("api/v1/customers/{id}")
    public CustomerResponse findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return CustomerResponse.from(customer);
    }

    @PutMapping("api/v1/customers/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
    ){
           Customer customer= customerService.update(
                   id,
                   request.name(),
                   request.email(),
                   request.phone()
           );
           return CustomerResponse.from(customer);
    }

    @DeleteMapping("api/v1/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        customerService.delete(id);
    }
}
