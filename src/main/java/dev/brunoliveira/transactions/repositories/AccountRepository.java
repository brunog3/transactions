package dev.brunoliveira.transactions.repositories;

import dev.brunoliveira.transactions.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}
