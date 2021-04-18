package dev.brunoliveira.transactions.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.brunoliveira.transactions.domain.entities.OperationType;
import dev.brunoliveira.transactions.domain.enums.Operation;
import dev.brunoliveira.transactions.domain.exception.OperationTypeNotFoundException;
import dev.brunoliveira.transactions.domain.repositories.OperationTypeRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperationTypeServiceTest {

  private static final Long OPERATION_TYPE_ID = 99l;
  private static final Operation OPERATION = Operation.CREDIT;
  private static final String OPERATION_DESCRIPTION = "PAGAMENTO";
  private static final OperationType OPERATION_TYPE = createOperationType();

  @Mock private OperationTypeRepository repository;
  @InjectMocks private OperationTypeService service;

  @Test(expected = OperationTypeNotFoundException.class)
  public void shouldThrowExceptionWhenOperationTypeIdNotFound() {
    service.findById(OPERATION_TYPE_ID);
  }

  @Test
  public void shouldReturnOperationTypeById() {
    when(repository.findById(OPERATION_TYPE_ID)).thenReturn(Optional.of(OPERATION_TYPE));

    var operationType = service.findById(OPERATION_TYPE_ID);

    assertThat(operationType).isNotNull();
    assertThat(operationType.getId()).isEqualTo(OPERATION_TYPE_ID);
    assertThat(operationType.getOperation()).isEqualTo(OPERATION);
    assertThat(operationType.getDescription()).isEqualTo(OPERATION_DESCRIPTION);
  }

  private static OperationType createOperationType() {
    return OperationType.builder()
        .id(OPERATION_TYPE_ID)
        .operation(OPERATION)
        .description(OPERATION_DESCRIPTION)
        .build();
  }
}
