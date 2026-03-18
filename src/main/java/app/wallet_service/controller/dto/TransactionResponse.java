package app.wallet_service.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import app.wallet_service.entity.Transaction;
import app.wallet_service.entity.enums.TransactionType;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        LocalDate date,
        TransactionType type) {

    public static TransactionResponse fromEntity(Transaction entity) {
        return new TransactionResponse(
                entity.getId(),
                entity.getAmount(),
                entity.getDescription(),
                entity.getDate(),
                entity.getType());
    }

}
