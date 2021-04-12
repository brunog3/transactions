package dev.brunoliveira.transactions.entities;

import dev.brunoliveira.transactions.enums.Operation;
import java.util.UUID;
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

  @Id @GeneratedValue private UUID id;
  private String description;

  @Enumerated(EnumType.STRING)
  private Operation operation;
}
