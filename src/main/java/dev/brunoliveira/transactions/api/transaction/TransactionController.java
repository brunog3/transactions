package dev.brunoliveira.transactions.api.transaction;

import dev.brunoliveira.transactions.domain.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

  @Autowired private final TransactionService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewTransaction(@RequestBody TransactionRequest request) {
    service.save(request.toEntity());
  }
}
