package dev.reportapi.controller;

import dev.reportapi.model.Customer;
import dev.reportapi.model.CustomerV1;
import dev.reportapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Customer> customers = customerService.getCustomers(page, pageSize);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/by-account-type")
    public ResponseEntity<List<CustomerV1>> getCustomersByAccountType( String accountType) {
        List<CustomerV1> customers = customerService.getCustomersByAccountType(accountType);
        return ResponseEntity.ok(customers);
    }

}
