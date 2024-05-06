package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;

public interface ContractService {
    ContractResponseDto createContract(ContractCreateRequestDto contractCreateRequest);

    ContractPageResponseDto findContracts(ContractPageRequestDto contractPageRequest);
}
