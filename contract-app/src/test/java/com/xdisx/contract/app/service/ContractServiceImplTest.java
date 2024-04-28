package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.mock.ContractMock;
import com.xdisx.contract.app.repository.db.ContractRepository;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
