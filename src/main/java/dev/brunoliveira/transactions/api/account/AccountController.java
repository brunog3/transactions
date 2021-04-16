package dev.brunoliveira.transactions.api.account;

import dev.brunoliveira.transactions.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController("/accounts")
public class AccountController {

  private final AccountService service;

  @GetMapping("/:accountId")
  public AccountResponse getAccountById(@PathParam(":accountId") Long id) {
    return null;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody AccountRequest request) {}
}
