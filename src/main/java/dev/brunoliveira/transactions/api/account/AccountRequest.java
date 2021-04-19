package dev.brunoliveira.transactions.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.brunoliveira.transactions.domain.entities.Account;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

  @JsonProperty("document_number")
  String documentNumber;

  public Account toEntity() {
    return Account.builder().documentNumber(documentNumber).build();
  }
}
