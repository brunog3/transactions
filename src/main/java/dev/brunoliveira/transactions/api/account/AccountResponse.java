package dev.brunoliveira.transactions.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.brunoliveira.transactions.domain.entities.Account;
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
public class AccountResponse {

  private Long id;

  @JsonProperty("document_number")
  private String documentNumber;

  public static AccountResponse of(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .documentNumber(account.getDocumentNumber())
        .build();
  }
}
