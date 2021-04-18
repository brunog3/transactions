package dev.brunoliveira.transactions.domain.repositories;

import dev.brunoliveira.transactions.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {}
