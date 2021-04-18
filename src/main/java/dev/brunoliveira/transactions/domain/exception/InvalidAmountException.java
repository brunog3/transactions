package dev.brunoliveira.transactions.domain.exception;

public class InvalidAmountException extends RuntimeException {

  private static final String MESSAGE = "Amount is not valid";

  public InvalidAmountException() {
    super(MESSAGE);
  }
}
