package dev.brunoliveira.transactions.api.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AccountRequestTest {

  private static final String DOCUMENT_NUMBER = "123789";

  @Test
  public void shouldProducesAccountEntityFromAccountResponse() {
    var request = AccountRequest.builder().documentNumber(DOCUMENT_NUMBER).build();

    var entity = request.toEntity();

    assertThat(entity).isNotNull();
    assertThat(entity.getId()).isNull();
    assertThat(entity.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
  }
}
