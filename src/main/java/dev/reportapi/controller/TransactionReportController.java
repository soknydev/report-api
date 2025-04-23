package dev.reportapi.controller;

import dev.reportapi.model.TransactionReport;
import dev.reportapi.service.TransactionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
