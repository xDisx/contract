package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;

public interface ContractService {
    ContractResponseDto createContract(ContractCreateRequestDto contractCreateRequest);
}
