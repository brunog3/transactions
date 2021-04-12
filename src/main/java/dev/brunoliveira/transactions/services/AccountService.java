package dev.brunoliveira.transactions.services;

import dev.brunoliveira.transactions.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;

}
