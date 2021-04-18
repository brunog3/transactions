package dev.brunoliveira.transactions.domain.entities;

import dev.brunoliveira.transactions.domain.enums.Operation;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operations_types")
public class OperationType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @Enumerated(EnumType.STRING)
  private Operation operation;
}
