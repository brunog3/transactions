package dev.brunoliveira.transactions.services;

import dev.brunoliveira.transactions.entities.OperationType;
import dev.brunoliveira.transactions.repositories.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationTypeService {

  private final OperationTypeRepository repository;

  public OperationType findById(Long id) {
    return repository.findById(id).orElseThrow();
  }
}
