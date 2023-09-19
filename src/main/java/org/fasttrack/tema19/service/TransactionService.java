package org.fasttrack.tema19.service;

import org.fasttrack.tema19.model.Transaction;
import org.fasttrack.tema19.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final List<Transaction> transactions;

    @Autowired
    public TransactionService(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions(String product, TransactionType type, Double minAmount, Double maxAmount) {
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(t -> product == null || t.getProduct().equals(product))
                .filter(t -> type == null || t.getType() == type)
                .filter(t -> minAmount == null || t.getAmount() >= minAmount)
                .filter(t -> maxAmount == null || t.getAmount() <= maxAmount)
                .collect(Collectors.toList());
        return filteredTransactions;
    }

    public Transaction getTransactionById(int id) {
        Optional<Transaction> optionalTransaction = transactions.stream()
                .filter(transaction -> transaction.getId() == id)
                .findFirst();
        return optionalTransaction.orElse(null);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void updateTransaction(int id, Transaction transaction) {

    }

}
