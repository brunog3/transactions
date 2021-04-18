package dev.brunoliveira.transactions.domain.exception;

public class OperationTypeNotFoundException extends RuntimeException {

  private static final String MESSAGE = "Operation type not found";

  public OperationTypeNotFoundException() {
    super(MESSAGE);
  }
}
