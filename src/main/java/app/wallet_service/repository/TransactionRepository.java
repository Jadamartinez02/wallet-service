package app.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.wallet_service.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
