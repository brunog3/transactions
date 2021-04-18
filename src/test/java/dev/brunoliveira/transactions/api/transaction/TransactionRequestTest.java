package dev.brunoliveira.transactions.api.transaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.Test;

public class TransactionRequestTest {
  private static final Long ACCOUNT_ID = 4l;
  private static final Long OPERATION_TYPE_ID = 8l;
  private static final BigDecimal AMOUNT = BigDecimal.TEN;
  private static final TransactionRequest REQUEST = createRequest();

  @Test
  public void shouldProducesTransactionEntityFromTransactionRequest() {
    var entity = REQUEST.toEntity();

    assertThat(entity).isNotNull();
    assertThat(entity.getId()).isNull();
    assertThat(entity.getAccount().getId()).isEqualTo(ACCOUNT_ID);
    assertThat(entity.getOperationType().getId()).isEqualTo(OPERATION_TYPE_ID);
    assertThat(entity.getAmount()).isEqualTo(AMOUNT);
  }

  private static final TransactionRequest createRequest() {
    return TransactionRequest.builder()
        .accountId(ACCOUNT_ID)
        .operationTypeId(OPERATION_TYPE_ID)
        .amount(AMOUNT)
        .build();
  }
}
