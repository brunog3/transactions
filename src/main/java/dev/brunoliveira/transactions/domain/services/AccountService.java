package dev.brunoliveira.transactions.domain.services;

import static java.util.Objects.isNull;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidDocumentNumberException;
import dev.brunoliveira.transactions.domain.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;

  public Account findById(Long accountId) {
    return repository.findById(accountId).orElseThrow(AccountNotFoundException::new);
  }

  public void save(Account account) {
    validate(account);
    repository.save(account);
  }

  private void validate(Account account) {
    if (isNull(account.getDocumentNumber()) || account.getDocumentNumber().isBlank()) {
      throw new InvalidDocumentNumberException();
    }
  }
}
