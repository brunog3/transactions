package dev.brunoliveira.transactions.api.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.entities.Transaction;
import dev.brunoliveira.transactions.domain.services.TransactionService;
import java.math.BigDecimal;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

  private static final String BASE_PATH = "/transactions";
  private static final Long ACCOUNT_ID = 33l;
  private static final Long OPERATION_TYPE_ID = 44l;
  private static final BigDecimal AMOUNT = BigDecimal.ONE;
  private static final Transaction TRANSACTION = createTransaction();
  private static final TransactionRequest REQUEST = createTransactionRequest();

  @Autowired private MockMvc mockMvc;
  @MockBean private TransactionService service;
  @Captor ArgumentCaptor<Transaction> transactionArgumentCaptor;

  @Test
  @SneakyThrows
  public void shouldCreateNewTransaction() {
    mockMvc
        .perform(
            post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(REQUEST)))
        .andExpect(status().isCreated());

    verify(service).save(transactionArgumentCaptor.capture());
    var transaction = transactionArgumentCaptor.getValue();
    assertThat(transaction).isNotNull();
    assertThat(transaction.getAccount().getId()).isEqualTo(ACCOUNT_ID);
    assertThat(transaction.getOperationType().getId()).isEqualTo(OPERATION_TYPE_ID);
    assertThat(transaction.getAmount()).isEqualTo(AMOUNT);
  }

  private static Transaction createTransaction() {
    return Transaction.builder()
        .account(Account.builder().id(ACCOUNT_ID).build())
        .operationType(OperationType.builder().id(OPERATION_TYPE_ID).build())
        .amount(AMOUNT)
        .build();
  }

  private static TransactionRequest createTransactionRequest() {
    return TransactionRequest.builder()
        .accountId(ACCOUNT_ID)
        .operationTypeId(OPERATION_TYPE_ID)
        .amount(AMOUNT)
        .build();
  }

  private ObjectMapper getMapper() {
    return new ObjectMapper();
  }
}
