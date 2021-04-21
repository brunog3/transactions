package dev.brunoliveira.transactions.api.account;

import static org.assertj.core.api.Assertions.assertThat;

import dev.brunoliveira.transactions.domain.entities.Account;
import java.math.BigDecimal;
import org.junit.Test;

public class AccountResponseTest {
  private static final Long ACCOUNT_ID = 1l;
  private static final String DOCUMENT_NUMBER = "348234234";
  private static final BigDecimal AVAILABLE_ACCOUNT_LIMIT = BigDecimal.valueOf(500);
  private static final Account ACCOUNT = createAccount();

  @Test
  public void shouldProducesAccountResponseFromAccountEntity() {
    var response = AccountResponse.of(ACCOUNT);

    assertThat(response).isNotNull();
    assertThat(response.getId()).isEqualTo(ACCOUNT_ID);
    assertThat(response.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
    assertThat(response.getAvailableCreditLimit()).isEqualTo(AVAILABLE_ACCOUNT_LIMIT);
  }

  private static Account createAccount() {
    return Account.builder().id(ACCOUNT_ID).documentNumber(DOCUMENT_NUMBER).availableCreditLimit(AVAILABLE_ACCOUNT_LIMIT).build();
  }
}
