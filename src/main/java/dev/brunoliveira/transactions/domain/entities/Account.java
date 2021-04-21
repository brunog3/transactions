package dev.brunoliveira.transactions.domain.entities;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String documentNumber;

  private BigDecimal availableCreditLimit;
}
