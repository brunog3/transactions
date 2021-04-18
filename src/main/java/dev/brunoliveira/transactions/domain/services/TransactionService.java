package dev.brunoliveira.transactions.domain.services;

import static java.util.Objects.isNull;

import dev.brunoliveira.transactions.domain.entities.Transaction;
import dev.brunoliveira.transactions.domain.enums.Operation;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidAmountException;
import dev.brunoliveira.transactions.domain.exception.OperationTypeNotFoundException;
import dev.brunoliveira.transactions.domain.repositories.TransactionRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository repository;
  private final AccountService accountService;
  private final OperationTypeService operationTypeService;

  public void save(Transaction transaction) {
    validate(transaction);
    loadDependencies(transaction);
    defineAmountByOperation(transaction);
    repository.save(transaction);
  }

  private void validate(Transaction transaction) {
    var account = transaction.getAccount();
    if (isNull(account) || isNull(account.getId())) {
      throw new AccountNotFoundException();
    }

    var operationType = transaction.getOperationType();
    if (isNull(operationType) || isNull(operationType.getId())) {
      throw new OperationTypeNotFoundException();
    }

    if (isNull(transaction.getAmount())
        || BigDecimal.ZERO.compareTo(transaction.getAmount()) >= 0) {
      throw new InvalidAmountException();
    }
  }

  private void defineAmountByOperation(Transaction transaction) {
    var amount = transaction.getAmount().abs();
    var operation = transaction.getOperationType().getOperation();
    if (Operation.DEBIT.equals(operation)) {
      amount = amount.negate();
    }
    transaction.setAmount(amount);
  }

  private void loadDependencies(Transaction transaction) {
    transaction.setAccount(accountService.findById(transaction.getAccount().getId()));
    transaction.setOperationType(
        operationTypeService.findById(transaction.getOperationType().getId()));
  }
}
