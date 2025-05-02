package dev.reportapi.service.Impl;

import dev.reportapi.model.Customer;
import dev.reportapi.model.CustomerV1;
import dev.reportapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers(Integer page, Integer pageSize) {
        return jdbcTemplate.execute((Connection connection) -> {
            List<Customer> customers = new ArrayList<>();
            try (CallableStatement cs = connection.prepareCall("{CAll ORA1.GET_CUSTOMERS(?, ?, ?)}")) {
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

    @Override
    public List<CustomerV1> getCustomersByAccountType(String accountType) {
        log.info(accountType);
        return jdbcTemplate.execute((Connection connection) -> {
            List<CustomerV1> customers = new ArrayList<>();
            try (CallableStatement cs = connection.prepareCall("{CALL ORA1.GET_CUSTOMERS_BY_ACCOUNT_TYPES(?, ?)}")) {
                cs.setString(1, accountType); // e.g., "S,C,D"
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                    while (rs.next()) {
                        CustomerV1 customer = new CustomerV1();
                        customer.setFullName(rs.getString("FULL_NAME"));
                        customer.setPhone(rs.getString("PHONE"));
                        customer.setAccountId(rs.getString("ACCOUNT_ID"));
                        customer.setAccountType(rs.getString("ACCOUNT_TYPE"));
                        customer.setBalance(rs.getBigDecimal("BALANCE"));
                        customer.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                        customer.setAssignedDate(rs.getDate("ASSIGNED_DATE"));
                        customer.setEmployeeName(rs.getString("EMP_NAME"));
                        customer.setPosition(rs.getString("POSITION"));
                        customer.setBranchId(rs.getString("BRANCH_ID"));
                        customer.setBranchName(rs.getString("BRANCH_NAME"));
                        customer.setLocation(rs.getString("LOCATION"));
                        customers.add(customer);
                    }
                }
            }
            return customers;
        });
    }



}
