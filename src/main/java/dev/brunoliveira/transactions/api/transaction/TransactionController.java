package dev.brunoliveira.transactions.api.transaction;

import dev.brunoliveira.transactions.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/transactions")
public class TransactionController {

  @Autowired private final TransactionService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewTransaction(@RequestBody TransactionRequest request) {}
}
