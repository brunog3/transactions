package dev.brunoliveira.transactions.repositories;

import dev.brunoliveira.transactions.entities.Transaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {}
