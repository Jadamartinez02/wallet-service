package app.wallet_service.controller.dto;

import java.math.BigDecimal;

import app.wallet_service.entity.enums.TransactionType;

public record TransactionRequest(
        BigDecimal amount,
        String description,
        TransactionType type) {

}
