package app.wallet_service.service;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.stereotype.Service;

import app.wallet_service.entity.Transaction;
import app.wallet_service.entity.enums.TransactionType;
import app.wallet_service.repository.TransactionRepository;
import static app.wallet_service.entity.Transaction.isIncome;
import static app.wallet_service.entity.Transaction.isExpense;

@Service
public class TransactionService {
    
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }
   
    public Transaction saveTransaction(Transaction transaction) {

        return repository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return repository
                .findAll()
                .stream()
                .filter(t -> t.getType() == type)
                .toList();
    }

    public BigDecimal calculateBalance() {
        List<Transaction> transactions = repository.findAll();

        BigDecimal totalIncomes = transactions.stream()
                .filter(isIncome())
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
                .filter(isExpense())
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalIncomes.subtract(totalExpense);
    }

}
