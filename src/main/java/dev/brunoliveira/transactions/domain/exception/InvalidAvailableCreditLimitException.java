package dev.brunoliveira.transactions.domain.exception;

public class InvalidAvailableCreditLimitException extends RuntimeException {

  private static final String MESSAGE = "AvailableCreditLimit is not valid";

  public InvalidAvailableCreditLimitException() {
    super(MESSAGE);
  }
}
