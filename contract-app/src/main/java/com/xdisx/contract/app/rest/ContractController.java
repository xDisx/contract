package com.xdisx.contract.app.rest;

import com.xdisx.contract.api.ContractApi;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

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
}
