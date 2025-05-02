package dev.reportapi.controller;

import dev.reportapi.model.TransactionReport;
import dev.reportapi.service.TransactionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class TransactionReportController {

    private final TransactionReportService transactionReportService;

    @GetMapping("/transactions/{year}")
    public List<TransactionReport> getTransactionsByYear(@PathVariable String year) {
        return transactionReportService.getTransactionsByYear(year);
    }

}
