package app.wallet_service.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Predicate;

import app.wallet_service.entity.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, String description, LocalDate date, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public static Predicate<Transaction> isIncome() {
        return t -> t.getType() == TransactionType.INCOME;
    }

    public static Predicate<Transaction> isExpense() {
        return t -> t.getType() == TransactionType.EXPENSE;
    }
}
