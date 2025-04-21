package dev.reportapi.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transaction {
    private BigDecimal amount;
    private Date txnDate;
    private String txnType;
}
