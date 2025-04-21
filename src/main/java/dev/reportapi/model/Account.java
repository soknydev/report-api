package dev.reportapi.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Account {
    private String accountId;
    private String accountType;
    private BigDecimal balance;
    private Date openedDate;
}
