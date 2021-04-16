package dev.brunoliveira.transactions.services;

import dev.brunoliveira.transactions.entities.Account;
import dev.brunoliveira.transactions.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;

  private void save(Account account) {
    repository.save(account);
  }

  private Account findById(Long accountId) {
    return repository.findById(accountId).orElseThrow();
  }
}
