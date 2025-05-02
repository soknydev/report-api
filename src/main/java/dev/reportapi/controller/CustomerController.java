package dev.reportapi.controller;

import dev.reportapi.model.Customer;
import dev.reportapi.model.CustomerV1;
import dev.reportapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return customerService.getCustomers(page, pageSize);
    }

    @GetMapping("/by-account-type")
    public List<CustomerV1> getCustomersByAccountType( String accountType) {
        return customerService.getCustomersByAccountType(accountType);
    }

}
