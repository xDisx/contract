package com.xdisx.contract.app.rest;

import com.xdisx.contract.api.ContractApi;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.request.UpdateContractStatusRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController implements ContractApi {
  private final ContractService contractService;

  @Override
  public ContractResponseDto createContract(ContractCreateRequestDto contractCreateRequest) {
    log.info("Received create contract request: {}", contractCreateRequest);
    return contractService.createContract(contractCreateRequest);
  }

  @Override
  public ContractPageResponseDto getContracts(ContractPageRequestDto contractPageRequest) {
    log.info("Received get contracts request: {}", contractPageRequest);
    return contractService.findContracts(contractPageRequest);
  }

  @Override
  public ContractResponseDto getContract(BigInteger id) {
    log.info("Received get contract request: {}", id);
    return contractService.getContract(id);
  }

  @Override
  public ContractResponseDto updateContractStatus(BigInteger id, UpdateContractStatusRequestDto updateContractStatusRequest) {
    log.info("Received update contract status request: {}", updateContractStatusRequest);
    return contractService.updateContractStatus(id, updateContractStatusRequest);
  }
}
