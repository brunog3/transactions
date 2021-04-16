package dev.brunoliveira.transactions.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

  @PrePersist
  public void onPersist() {
    createdAt = LocalDateTime.now();
  }
}
