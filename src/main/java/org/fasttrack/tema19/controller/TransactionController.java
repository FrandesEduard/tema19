package org.fasttrack.tema19.controller;

import org.fasttrack.tema19.model.Transaction;
import org.fasttrack.tema19.model.TransactionType;
import org.fasttrack.tema19.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransaction(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount
    ) {
        return transactionService.getTransactions(product, type, minAmount, maxAmount);
    }
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }
    @PostMapping
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

}
