package dev.reportapi.service;

import dev.reportapi.model.Customer;
import dev.reportapi.model.CustomerV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getCustomers(Integer page, Integer pageSize );

    List<CustomerV1> getCustomersByAccountType(String accountType );

}
