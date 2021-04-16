package dev.brunoliveira.transactions.services;

import dev.brunoliveira.transactions.entities.Transaction;
import dev.brunoliveira.transactions.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository repository;

  public void save(Transaction transaction) {
    repository.save(transaction);
  }
}
