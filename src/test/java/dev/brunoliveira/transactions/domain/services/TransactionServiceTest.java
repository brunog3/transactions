package dev.brunoliveira.transactions.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.entities.Transaction;
import dev.brunoliveira.transactions.domain.enums.Operation;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidAmountException;
import dev.brunoliveira.transactions.domain.exception.OperationTypeNotFoundException;
import dev.brunoliveira.transactions.domain.repositories.TransactionRepository;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
  private static final Long ACCOUNT_ID = 1l;
  private static final Long CREDIT_OPERATION_TYPE_ID = 11l;
  private static final Long DEBIT_OPERATION_TYPE_ID = 22l;
  private static final BigDecimal AMOUNT = BigDecimal.TEN;

  @Mock private AccountService accountService;
  @Mock private OperationTypeService operationTypeService;
  @Mock private TransactionRepository repository;
  @InjectMocks private TransactionService service;
  @Captor private ArgumentCaptor<Transaction> transactionArgumentCaptor;

  @Test
  public void shouldCreateNewTransactionUsingCreditOperation() {
    when(accountService.findById(ACCOUNT_ID)).thenReturn(createAccount());
    when(operationTypeService.findById(CREDIT_OPERATION_TYPE_ID))
        .thenReturn(createCreditOperationType());

    var transaction = createCreditTransaction();
    service.save(transaction);

    verify(repository).save(transactionArgumentCaptor.capture());
    var persistedTransaction = transactionArgumentCaptor.getValue();
    assertThat(persistedTransaction).isNotNull();
    assertThat(persistedTransaction.getAmount()).isEqualTo(AMOUNT);
    assertThat(persistedTransaction.getAccount().getId()).isEqualTo(ACCOUNT_ID);
    assertThat(persistedTransaction.getOperationType().getId()).isEqualTo(CREDIT_OPERATION_TYPE_ID);
  }

  @Test
  public void shouldCreateNewTransactionUsingDebitOperation() {
    when(accountService.findById(ACCOUNT_ID)).thenReturn(createAccount());
    when(operationTypeService.findById(DEBIT_OPERATION_TYPE_ID))
        .thenReturn(createDebitOperationType());

    var transaction = createDebitTransaction();
    service.save(transaction);

    verify(repository).save(transactionArgumentCaptor.capture());
    var persistedTransaction = transactionArgumentCaptor.getValue();
    assertThat(persistedTransaction).isNotNull();
    assertThat(persistedTransaction.getAmount()).isEqualTo(AMOUNT.negate());
    assertThat(persistedTransaction.getAccount().getId()).isEqualTo(ACCOUNT_ID);
    assertThat(persistedTransaction.getOperationType().getId()).isEqualTo(DEBIT_OPERATION_TYPE_ID);
  }

  @Test(expected = InvalidAmountException.class)
  public void shouldThrowExceptionWhenAmountIsInvalid() {
    var transaction = createCreditTransaction();
    transaction.setAmount(null);
    service.save(transaction);
  }

  @Test(expected = InvalidAmountException.class)
  public void shouldThrowExceptionWhenAmountIsEmpty() {
    var transaction = createCreditTransaction();
    transaction.setAmount(BigDecimal.ZERO);
    service.save(transaction);
  }

  @Test(expected = AccountNotFoundException.class)
  public void shouldThrowExceptionWhenAccountIsInvalid() {
    var transaction = createCreditTransaction();
    transaction.getAccount().setId(null);

    service.save(transaction);
  }

  @Test(expected = OperationTypeNotFoundException.class)
  public void shouldThrowExceptionWhenOperationTypeIsInvalid() {
    var transaction = createCreditTransaction();
    transaction.getOperationType().setId(null);

    service.save(transaction);
  }

  private static Transaction createCreditTransaction() {
    return createTransaction(createCreditOperationType());
  }

  private static Transaction createDebitTransaction() {
    return createTransaction(createDebitOperationType());
  }

  private static Transaction createTransaction(OperationType operationType) {
    return Transaction.builder()
        .account(createAccount())
        .operationType(operationType)
        .amount(AMOUNT)
        .build();
  }

  private static Account createAccount() {
    return Account.builder().id(ACCOUNT_ID).build();
  }

  private static OperationType createCreditOperationType() {
    return OperationType.builder().id(CREDIT_OPERATION_TYPE_ID).operation(Operation.CREDIT).build();
  }

  private static OperationType createDebitOperationType() {
    return OperationType.builder().id(DEBIT_OPERATION_TYPE_ID).operation(Operation.DEBIT).build();
  }
}
