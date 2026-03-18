package app.wallet_service.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.wallet_service.controller.dto.TransactionRequest;
import app.wallet_service.controller.dto.TransactionResponse;
import app.wallet_service.entity.Transaction;
import app.wallet_service.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransactionResponse(@RequestBody TransactionRequest request) {
        Transaction transactionToSave = new Transaction();
        transactionToSave.setAmount(request.amount());
        transactionToSave.setDescription(request.description());
        transactionToSave.setType(request.type());
        transactionToSave.setDate(LocalDate.now());

        Transaction saveTransaction = service.saveTransaction(transactionToSave);
        return TransactionResponse.fromEntity(saveTransaction);
    }
    @GetMapping
    public List<TransactionResponse> getAll() {
        return service.getAllTransactions().stream()
                .map(TransactionResponse::fromEntity)
                .toList();
    }
    public Map<String, BigDecimal> getBalance(){
        return Map.of("balance", service.calculateBalance());
    }

}
