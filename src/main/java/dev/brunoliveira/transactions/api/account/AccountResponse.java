package dev.brunoliveira.transactions.api.account;

import dev.brunoliveira.transactions.entities.Account;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

  private Long id;
  private String documentNumber;

  public static AccountResponse of(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .documentNumber(account.getDocumentNumber())
        .build();
  }
}
