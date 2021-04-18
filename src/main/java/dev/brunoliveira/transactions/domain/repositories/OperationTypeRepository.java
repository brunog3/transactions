package dev.brunoliveira.transactions.domain.repositories;

import dev.brunoliveira.transactions.domain.entities.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {}
