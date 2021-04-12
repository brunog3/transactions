package dev.brunoliveira.transactions.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

  @Id @GeneratedValue private UUID id;
  private String documentNumber;
}
