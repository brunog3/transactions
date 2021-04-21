package dev.brunoliveira.transactions.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidAvailableCreditLimitException;
import dev.brunoliveira.transactions.domain.exception.InvalidDocumentNumberException;
import dev.brunoliveira.transactions.domain.repositories.AccountRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private static final Long ACCOUNT_ID = 1l;
  private static final String DOCUMENT_NUMBER = "12345678900";
  private static final String INVALID_DOCUMENT_NUMBER = "";
  private static final BigDecimal AVAILABLE_CREDIT_LIMIT = BigDecimal.valueOf(500);
  private static final Account ACCOUNT = createAccount();

  @Mock private AccountRepository repository;
  @InjectMocks private AccountService service;
  @Captor private ArgumentCaptor<Account> accountArgumentCaptor;

  @Test(expected = AccountNotFoundException.class)
  public void shouldThrowExceptionWhenAccountIdNotFound() {
    service.findById(ACCOUNT_ID);
  }

  @Test
  public void shouldReturnAccountById() {
    when(repository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT));

    var account = service.findById(ACCOUNT_ID);

    assertThat(account).isNotNull();
    assertThat(account.getId()).isEqualTo(ACCOUNT_ID);
    assertThat(account.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
    assertThat(account.getAvailableCreditLimit()).isEqualTo(AVAILABLE_CREDIT_LIMIT);
  }

  @Test
  public void shouldCreateNewAccount() {
    var account =
        Account.builder()
            .documentNumber(DOCUMENT_NUMBER)
            .availableCreditLimit(AVAILABLE_CREDIT_LIMIT)
            .build();

    service.save(account);

    verify(repository).save(account);
  }

  @Test(expected = InvalidDocumentNumberException.class)
  public void shouldThrowExceptionWhenDocumentNumberIsInvalid() {
    var account = Account.builder().documentNumber(INVALID_DOCUMENT_NUMBER).build();

    service.save(account);
  }

  @Test(expected = InvalidDocumentNumberException.class)
  public void shouldThrowExceptionWhenDocumentNumberIsEmpty() {
    service.save(Account.builder().build());
  }

  @Test(expected = InvalidAvailableCreditLimitException.class)
  public void shouldThrowExceptionWhenAvailableCreditLimitIsInvalid() {
    service.save(Account.builder().documentNumber(DOCUMENT_NUMBER).build());
  }

  @Test
  public void shouldUpdateAccountWhenLimitNotExcceded() {
    service.updateCreditLimit(createAccount(), BigDecimal.valueOf(400).negate());

    verify(repository).save(accountArgumentCaptor.capture());
    var account = accountArgumentCaptor.getValue();
    assertThat(account.getAvailableCreditLimit()).isEqualTo(BigDecimal.valueOf(100));
  }

  private static Account createAccount() {
    return Account.builder()
        .id(ACCOUNT_ID)
        .documentNumber(DOCUMENT_NUMBER)
        .availableCreditLimit(AVAILABLE_CREDIT_LIMIT)
        .build();
  }
}
