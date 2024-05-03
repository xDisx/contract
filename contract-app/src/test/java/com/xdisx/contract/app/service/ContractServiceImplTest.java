package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.api.exception.ContractCreateException;
import com.xdisx.contract.app.mock.ContractMock;
import com.xdisx.contract.app.repository.db.ContractRepository;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static com.xdisx.contract.app.service.ContractServiceImpl.CONTRACT_SAVE_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {
  @Mock
  private ContractRepository contractRepository;

  @InjectMocks
  private ContractServiceImpl classUnderTest;

  @Test
  void createContract() {
    ContractCreateRequestDto requestDto = ContractMock.getCreateContractRequest();
    ContractEntity contract = ContractMock.getContractEntity(requestDto);
    ContractEntity contract2 = ContractMock.getContractEntity(requestDto);
    assertEquals(contract, contract2);

    when(contractRepository.saveAndFlush(argThat(arg ->requestDto.getContractType().equals(arg.getContractType())))).thenReturn(contract);
    var savedContract = classUnderTest.createContract(requestDto);

    verify(contractRepository).saveAndFlush(argThat(arg -> {
      assertEquals(requestDto.getContractType(), arg.getContractType());
      return true;
    }));

    assertNotNull(savedContract);
    assertContractResponseWithRequest(requestDto, savedContract);
  }

  private void assertContractResponseWithRequest(ContractCreateRequestDto requestDto, ContractResponseDto responseDto) {
    assertEquals(requestDto.getContractType(), responseDto.getContractType());
  }

  @Test
  void createContract_throwsExceptionWhenDataIntegrityIsViolated() {
    ContractCreateRequestDto requestDto = ContractMock.getCreateContractRequest();

    doThrow(new DataIntegrityViolationException("Database error"))
            .when(contractRepository)
            .saveAndFlush(any(ContractEntity.class));

    ContractCreateException thrown = assertThrows(ContractCreateException.class, () -> {
      classUnderTest.createContract(requestDto);
    }, "Expected createContract to throw, but it didn't");

    assertEquals(CONTRACT_SAVE_ERROR, thrown.getMessage(), "Exception message does not match");
  }
}
