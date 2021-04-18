package dev.brunoliveira.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
// @EnableJpaRepositories(basePackages = "dev.brunoliveira.transactions")
// @ComponentScan(basePackages = "dev.brunoliveira.transactions")
public class TransactionsApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransactionsApplication.class, args);
  }
}
