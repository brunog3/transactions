package dev.brunoliveira.transactions.api.account;

import dev.brunoliveira.transactions.domain.entities.Account;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

  Long documentNumber;

  public Account toEntity() {
    return Account.builder().documentNumber(documentNumber).build();
  }
}
