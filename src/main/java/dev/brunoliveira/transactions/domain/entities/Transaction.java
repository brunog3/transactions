package dev.brunoliveira.transactions.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "account_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Account account;

  @JoinColumn(name = "operation_type_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private OperationType operationType;

  private BigDecimal amount;
  private LocalDateTime createdAt;

  public BigDecimal getAbsoluteAmount() {
    return amount.abs();
  }

  @PrePersist
  public void onPersist() {
    createdAt = LocalDateTime.now();
  }
}
