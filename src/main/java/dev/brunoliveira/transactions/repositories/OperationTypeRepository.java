package dev.brunoliveira.transactions.repositories;

import dev.brunoliveira.transactions.entities.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {}
