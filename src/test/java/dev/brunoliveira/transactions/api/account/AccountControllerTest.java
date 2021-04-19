package dev.brunoliveira.transactions.api.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.brunoliveira.transactions.domain.entities.Account;
import dev.brunoliveira.transactions.domain.exception.AccountNotFoundException;
import dev.brunoliveira.transactions.domain.exception.InvalidDocumentNumberException;
import dev.brunoliveira.transactions.domain.services.AccountService;
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
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
  private static final String BASE_PATH = "/accounts";
  private static final Long ACCOUNT_ID = 22l;
  private static final String DOCUMENT_NUMBER = "12345678900";
  private static final Account ACCOUNT = createAccount();

  @Value("classpath:api/account/account-request.json")
  private Resource accountRequestResource;

  @Value("classpath:api/account/account-response.json")
  private Resource accountResponseResource;

  @Autowired private MockMvc mockMvc;
  @MockBean private AccountService service;
  @Captor ArgumentCaptor<Account> accountArgumentCaptor;

  @Test
  @SneakyThrows
  public void shouldReturnAccountById() {
    when(service.findById(ACCOUNT_ID)).thenReturn(ACCOUNT);

    mockMvc
        .perform(get(BASE_PATH + "/{accountId}", ACCOUNT_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            content()
                .json(new String(Files.readAllBytes(accountResponseResource.getFile().toPath()))))
        .andReturn();

    verify(service).findById(ACCOUNT_ID);
  }

  @Test
  @SneakyThrows
  public void shouldReturn404WhenAccountNotFound() {
    when(service.findById(ACCOUNT_ID)).thenThrow(new AccountNotFoundException());

    mockMvc.perform(get(BASE_PATH + "/{accountId}", ACCOUNT_ID)).andExpect(status().isNotFound());

    verify(service).findById(ACCOUNT_ID);
  }

  @Test
  @SneakyThrows
  public void shouldCreateNewAccount() {
    var requestJson = new String(Files.readAllBytes(accountRequestResource.getFile().toPath()));

    mockMvc
        .perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(requestJson))
        .andExpect(status().isCreated());

    verify(service).save(accountArgumentCaptor.capture());
    var account = accountArgumentCaptor.getValue();
    assertThat(account).isNotNull();
    assertThat(account.getDocumentNumber()).isEqualTo(DOCUMENT_NUMBER);
  }

  @Test
  @SneakyThrows
  public void shouldReturn400WhenDocumentNumberIsInvalid() {
    doThrow(new InvalidDocumentNumberException()).when(service).save(any(Account.class));

    mockMvc
        .perform(
            post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(new AccountRequest())))
        .andExpect(status().isBadRequest());
  }

  private static Account createAccount() {
    return Account.builder().id(ACCOUNT_ID).documentNumber(DOCUMENT_NUMBER).build();
  }

  private ObjectMapper getMapper() {
    return new ObjectMapper();
  }
}
