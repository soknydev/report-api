package dev.reportapi.service;

import dev.reportapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getCustomers(Integer page, Integer pageSize );

}
