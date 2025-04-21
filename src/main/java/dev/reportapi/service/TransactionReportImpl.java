package dev.reportapi.service;

import dev.reportapi.model.TransactionReport;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionReportImpl implements TransactionReportService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TransactionReport> getTransactionsByYear(String year) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{call ORA1.GET_TRANSACTIONS_BY_DATE(?, ?)}");
            cs.setString(1, year);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(2);
            List<TransactionReport> reports = new ArrayList<>();
            while (rs.next()) {
                TransactionReport report = new TransactionReport();
                report.setFullName(rs.getString("FULL_NAME"));
                report.setAccountId(rs.getLong("ACCOUNT_ID"));
                report.setAccountType(rs.getString("ACCOUNT_TYPE"));
                report.setOpenedDate(rs.getDate("OPENED_DATE"));
                report.setBalance(rs.getBigDecimal("BALANCE"));
                report.setAmount(rs.getBigDecimal("AMOUNT"));
                report.setTxnDate(rs.getDate("TXN_DATE"));
                report.setTxnType(rs.getString("TXN_TYPE"));
                reports.add(report);
            }
            rs.close();
            cs.close();
            return reports;
        });
    }
}
