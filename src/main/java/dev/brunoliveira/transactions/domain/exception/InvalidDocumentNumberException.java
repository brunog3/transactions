package dev.brunoliveira.transactions.domain.exception;

public class InvalidDocumentNumberException extends RuntimeException {

  private static final String MESSAGE = "DocumentNumber is not valid";

  public InvalidDocumentNumberException() {
    super(MESSAGE);
  }
}
