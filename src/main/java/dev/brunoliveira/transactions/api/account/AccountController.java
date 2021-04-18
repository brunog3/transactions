package dev.brunoliveira.transactions.api.account;

import dev.brunoliveira.transactions.domain.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService service;

  @GetMapping("/{accountId}")
  public AccountResponse getAccountById(@PathVariable("accountId") Long id) {
    return AccountResponse.of(service.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody AccountRequest request) {
    service.save(request.toEntity());
  }
}
