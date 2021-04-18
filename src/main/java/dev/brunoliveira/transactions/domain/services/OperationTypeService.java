package dev.brunoliveira.transactions.domain.services;

import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.exception.OperationTypeNotFoundException;
import dev.brunoliveira.transactions.domain.repositories.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationTypeService {

  private final OperationTypeRepository repository;

  public OperationType findById(Long id) {
    return repository.findById(id).orElseThrow(OperationTypeNotFoundException::new);
  }
}
