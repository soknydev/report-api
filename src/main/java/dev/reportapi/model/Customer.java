package dev.reportapi.model;

import lombok.Data;

@Data
public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
}
