package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.api.exception.ContractCreateException;
import com.xdisx.contract.app.mock.ContractMock;
import com.xdisx.contract.app.mock.ContractPageDtoMock;
import com.xdisx.contract.app.repository.db.ContractRepository;
import com.xdisx.contract.app.repository.db.dto.ContractPageDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.contract.app.service.converter.ContractConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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

    when(contractRepository.saveAndFlush(argThat(arg ->requestDto.getCustomerId().equals(arg.getCustomerId()) && requestDto.getProductId().equals(arg.getProductId())))).thenReturn(contract);
    var savedContract = classUnderTest.createContract(requestDto);

    verify(contractRepository).saveAndFlush(argThat(arg -> {
      assertEquals(requestDto.getCustomerId(), arg.getCustomerId());
      assertEquals(requestDto.getDeviceCode(), arg.getDeviceCode());
      assertEquals(requestDto.getPeriod(), arg.getPeriod());
      assertEquals(requestDto.getProductId(), arg.getProductId());
      return true;
    }));

    assertNotNull(savedContract);
    assertContractResponseWithRequest(requestDto, savedContract);
  }

  private void assertContractResponseWithRequest(ContractCreateRequestDto requestDto, ContractResponseDto responseDto) {
    assertEquals(requestDto.getCustomerId(), responseDto.getCustomerId());
    assertEquals(requestDto.getDeviceCode(), responseDto.getDeviceCode());
    assertEquals(requestDto.getPeriod(), responseDto.getPeriod());
    assertEquals(requestDto.getProductId(), responseDto.getProductId());
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

  @Test
  void testFindContractsReturnsCorrectData() {
    Pageable pageable = PageRequest.of(0, 10);
    List<ContractPageDto> contractEntityList =
            List.of(new ContractPageDtoMock());
    Page<ContractPageDto> contractPage =
            new PageImpl<>(contractEntityList, pageable, contractEntityList.size());

    when(contractRepository.findBy(ArgumentMatchers.<Specification<ContractEntity>>any(), any()))
            .thenReturn(contractPage);

    ContractPageResponseDto result =
            classUnderTest.findContracts(
                    ContractPageRequestDto.builder().build());

    assertNotNull(result);
    assertEquals(contractEntityList.size(), result.getContracts().size());

    verify(contractRepository).findBy(ArgumentMatchers.<Specification<ContractEntity>>any(), any());
  }

  @Test
  void testFindContractsByCreatedOn() {
    Pageable pageable = PageRequest.of(0, 10);
    List<ContractPageDto> contractEntityList =
            List.of(new ContractPageDtoMock());
    Page<ContractPageDto> contractPage =
            new PageImpl<>(contractEntityList, pageable, contractEntityList.size());

    when(contractRepository.findBy(ArgumentMatchers.<Specification<ContractEntity>>any(), any()))
            .thenReturn(contractPage);

    ContractPageResponseDto result =
            classUnderTest.findContracts(
                    ContractPageRequestDto.builder().createdOn(LocalDate.now()).build());

    assertNotNull(result);
    assertEquals(contractEntityList.size(), result.getContracts().size());

    verify(contractRepository).findBy(ArgumentMatchers.<Specification<ContractEntity>>any(), any());
  }
}
