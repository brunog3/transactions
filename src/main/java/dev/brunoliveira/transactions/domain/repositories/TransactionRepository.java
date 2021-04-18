package dev.brunoliveira.transactions.domain.repositories;

import dev.brunoliveira.transactions.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
