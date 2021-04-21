package dev.brunoliveira.transactions.domain.exception;

public class NoCreditAvailableException extends RuntimeException {
  private static final String MESSAGE = "No credit available for this transaction";

  public NoCreditAvailableException() {
    super(MESSAGE);
  }
}
