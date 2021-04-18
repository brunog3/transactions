package dev.brunoliveira.transactions.api.transaction;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.entities.Transaction;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

  private Long accountId;
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
