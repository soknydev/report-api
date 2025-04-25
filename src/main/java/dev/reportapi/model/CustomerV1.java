package dev.reportapi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerV1 {
    private String fullName;
    private String phone;
    private String accountId;
    private String accountType;
    private BigDecimal balance;
    private String employeeId;
    private Date assignedDate;
    private String employeeName;
    private String position;
    private String branchId;
    private String branchName;
    private String location;
}
