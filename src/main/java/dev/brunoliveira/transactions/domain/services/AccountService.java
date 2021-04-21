package dev.brunoliveira.transactions.domain.services;

import static java.util.Objects.isNull;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidAvailableCreditLimitException;
import dev.brunoliveira.transactions.domain.exception.InvalidDocumentNumberException;
import dev.brunoliveira.transactions.domain.exception.NoCreditAvailableException;
import dev.brunoliveira.transactions.domain.repositories.AccountRepository;
import java.math.BigDecimal;
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

    if (isNull(account.getAvailableCreditLimit())
        || BigDecimal.ZERO.compareTo(account.getAvailableCreditLimit()) > 0) {
      throw new InvalidAvailableCreditLimitException();
    }
  }

  public void updateCreditLimit(Account account, BigDecimal amount) {
    var limit = account.getAvailableCreditLimit();
    var result = limit.add(amount);
    if (BigDecimal.ZERO.compareTo(result) > 0) {
      throw new NoCreditAvailableException();
    }
    account.setAvailableCreditLimit(result);
    repository.save(account);
  }
}
