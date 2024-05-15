package com.xdisx.contract.api;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.request.UpdateContractStatusRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.SpringQueryMap;

import java.math.BigInteger;

public interface ContractApi {
    String ROOT_PATH = "/xdisx";

    @PostMapping(ROOT_PATH + "/contract")
    @ResponseStatus(HttpStatus.CREATED)
    ContractResponseDto createContract(@Valid @RequestBody ContractCreateRequestDto contractCreateRequest);

    @GetMapping(ROOT_PATH + "/contracts")
    @ResponseStatus(HttpStatus.OK)
    ContractPageResponseDto getContracts(@Valid @SpringQueryMap ContractPageRequestDto contractPageRequest);

    @GetMapping(ROOT_PATH+"/contracts/{id}")
    @ResponseStatus(HttpStatus.OK)
    ContractResponseDto getContract(@PathVariable("id") @NotNull BigInteger id);

    @PutMapping(ROOT_PATH+"/contracts/{id}")
    @ResponseStatus(HttpStatus.OK)
    ContractResponseDto updateContractStatus(@PathVariable("id") @NotNull BigInteger id, @Valid @RequestBody UpdateContractStatusRequestDto updateContractStatusRequestDto);
}
