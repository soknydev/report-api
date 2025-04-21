package dev.reportapi.service;

import dev.reportapi.model.TransactionReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionReportService {

    List<TransactionReport> getTransactionsByYear(String year);

}
