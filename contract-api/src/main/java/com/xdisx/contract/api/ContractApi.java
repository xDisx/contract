package com.xdisx.contract.api;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface ContractApi {
    String ROOT_PATH = "/xdisx";

    @PostMapping(ROOT_PATH + "/contract")
    @ResponseStatus(HttpStatus.CREATED)
    ContractResponseDto createContract(@Valid @RequestBody ContractCreateRequestDto contractCreateRequest);
}
