package dev.brunoliveira.transactions.api.account;

import static org.assertj.core.api.Assertions.assertThat;

import dev.brunoliveira.transactions.domain.entities.Account;
import org.junit.Test;

public class AccountResponseTest {
  private static final Long ACCOUNT_ID = 1l;
  private static final Long DOCUMENT_NUMBER = 348234234l;
  private static final Account ACCOUNT = createAccount();

  @Test
  public void shouldProducesAccountResponseFromAccountEntity() {
    var response = AccountResponse.of(ACCOUNT);

    assertThat(response).isNotNull();
    assertThat(response.getId()).isEqualTo(ACCOUNT_ID);
    assertThat(response.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
  }

  private static Account createAccount() {
    return Account.builder().id(ACCOUNT_ID).documentNumber(DOCUMENT_NUMBER).build();
  }
}
