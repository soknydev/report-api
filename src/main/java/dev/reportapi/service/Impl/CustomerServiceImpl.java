package dev.reportapi.service.Impl;

import dev.reportapi.model.Customer;
import dev.reportapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers(Integer page, Integer pageSize) {
        return jdbcTemplate.execute((Connection connection) -> {
            List<Customer> customers = new ArrayList<>();
            try (CallableStatement cs = connection.prepareCall("{call ORA1.GET_CUSTOMERS(?, ?, ?)}")) {
                cs.setInt(1, page);
                cs.setInt(2, pageSize);
                cs.registerOutParameter(3, OracleTypes.CURSOR);
                cs.execute();
                try (ResultSet rs = (ResultSet) cs.getObject(3)) {
                    while (rs.next()) {
                        Customer customer = new Customer();
                        customer.setCustomerId(rs.getString("CUS_ID"));
                        customer.setFullName(rs.getString("FULL_NAME"));
                        customer.setEmail(rs.getString("EMAIL"));
                        customer.setPhone(rs.getString("PHONE"));
                        customers.add(customer);
                    }
                }
            }
            return customers;
        });
    }

}
