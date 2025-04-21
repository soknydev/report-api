package dev.reportapi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionReport {
    private String fullName;
    private Long accountId;
    private String accountType;
    private Date openedDate;
    private BigDecimal balance;
    private BigDecimal amount;
    private Date txnDate;
    private String txnType;
}
