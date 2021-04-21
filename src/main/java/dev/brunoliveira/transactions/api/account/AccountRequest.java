package dev.brunoliveira.transactions.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.brunoliveira.transactions.domain.entities.Account;
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
public class AccountRequest {

  @JsonProperty("document_number")
  String documentNumber;

  @JsonProperty("available_credit_limit")
  BigDecimal availableCreditLimit;

  public Account toEntity() {
    return Account.builder()
        .documentNumber(documentNumber)
        .availableCreditLimit(availableCreditLimit)
        .build();
  }
}
