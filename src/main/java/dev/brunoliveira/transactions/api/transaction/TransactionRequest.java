package dev.brunoliveira.transactions.api.transaction;

import dev.brunoliveira.transactions.entities.Account;
import dev.brunoliveira.transactions.entities.OperationType;
import dev.brunoliveira.transactions.entities.Transaction;
import lombok.*;

import java.math.BigDecimal;

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
