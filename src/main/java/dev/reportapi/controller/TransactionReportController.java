package dev.reportapi.controller;

import dev.reportapi.model.TransactionReport;
import dev.reportapi.service.TransactionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class TransactionReportController {

    private final TransactionReportService transactionReportService;

    @GetMapping("/transactions/{year}")
    public ResponseEntity<List<TransactionReport>> getTransactionsByYear(@PathVariable String year) {
        List<TransactionReport> reports = transactionReportService.getTransactionsByYear(year);
        return ResponseEntity.ok(reports);
    }

}
