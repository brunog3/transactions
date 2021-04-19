package dev.brunoliveira.transactions.api.transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.brunoliveira.transactions.domain.entities.Transaction;
import dev.brunoliveira.transactions.domain.services.TransactionService;
import java.math.BigDecimal;
import java.nio.file.Files;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

  private static final String BASE_PATH = "/transactions";
  private static final Long ACCOUNT_ID = 1l;
  private static final Long OPERATION_TYPE_ID = 1l;
  private static final BigDecimal AMOUNT = new BigDecimal("123.45");

  @Value("classpath:api/transaction/transaction-request.json")
  private Resource transactionRequestResource;

  @Autowired private MockMvc mockMvc;

  @MockBean private TransactionService service;
  @Captor ArgumentCaptor<Transaction> transactionArgumentCaptor;

  @Test
  @SneakyThrows
  public void shouldCreateNewTransaction() {
    var requestJson = new String(Files.readAllBytes(transactionRequestResource.getFile().toPath()));

    mockMvc
        .perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(requestJson))
        .andExpect(status().isCreated());

    verify(service).save(transactionArgumentCaptor.capture());
    var transaction = transactionArgumentCaptor.getValue();
    assertThat(transaction).isNotNull();
    assertThat(transaction.getAccount().getId()).isEqualTo(ACCOUNT_ID);
    assertThat(transaction.getOperationType().getId()).isEqualTo(OPERATION_TYPE_ID);
    assertThat(transaction.getAmount()).isEqualTo(AMOUNT);
  }
}
