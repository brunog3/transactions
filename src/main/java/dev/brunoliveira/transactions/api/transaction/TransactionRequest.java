package dev.brunoliveira.transactions.api.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.entities.Transaction;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

  @JsonProperty("account_id")
  private Long accountId;

  @JsonProperty("operation_type_id")
  private Long operationTypeId;

  private BigDecimal amount;

  public Transaction toEntity() {
    return Transaction.builder()
        .account(Account.builder().id(accountId).build())
        .operationType(OperationType.builder().id(operationTypeId).build())
        .amount(amount)
        .build();
  }
}
