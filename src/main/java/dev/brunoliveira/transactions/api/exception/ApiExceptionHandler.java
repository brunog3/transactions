package dev.brunoliveira.transactions.api.exception;

import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.OperationTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({AccountNotFoundException.class, OperationTypeNotFoundException.class})
  public ErrorResponse handleResourceNotFound(RuntimeException exception, WebRequest request) {
    return ErrorResponse.builder().message("Record not found").build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(RuntimeException.class)
  public ErrorResponse handleInvalidRequest(RuntimeException exception, WebRequest request) {
    return ErrorResponse.builder()
        .message("Unable to process this request: " + exception.getMessage())
        .build();
  }
}
