package dev.brunoliveira.transactions.api.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.Test;

public class AccountRequestTest {

  private static final String DOCUMENT_NUMBER = "123789";
  private static final BigDecimal AVAILABLE_ACCOUNT_LIMIT = BigDecimal.valueOf(500);

  @Test
  public void shouldProducesAccountEntityFromAccountResponse() {
    var request = AccountRequest.builder().documentNumber(DOCUMENT_NUMBER).availableCreditLimit(AVAILABLE_ACCOUNT_LIMIT).build();

    var entity = request.toEntity();

    assertThat(entity).isNotNull();
    assertThat(entity.getId()).isNull();
    assertThat(entity.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
    assertThat(entity.getAvailableCreditLimit()).isEqualTo(AVAILABLE_ACCOUNT_LIMIT);
  }
}
