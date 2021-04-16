package dev.brunoliveira.transactions.api.account;

import dev.brunoliveira.transactions.entities.Account;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountResponseTest {
  private static final Long ACCOUNT_ID = 1l;
  private static final String DOCUMENT_NUMBER = "348234234";
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
