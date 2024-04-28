package com.xdisx.contract.e2e.contract.steps.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.xdisx.contract.e2e.common.utils.ReadFileUtil.readJson;
import static org.junit.jupiter.api.Assertions.fail;

@Service
@RequiredArgsConstructor
@Slf4j
public class RequestBuilderServiceContract {
    private static final String JSON_READ_ERROR = "Failed to read the request JSON from %s";

    public ContractCreateRequestDto buildContractCreateRequest() {
        var filePath = "/json/contract/ContractCreateRequest.json";
        ContractCreateRequestDto requestJson = null;

        try {
            requestJson = readJson(ContractCreateRequestDto.class, filePath);
        } catch (JsonProcessingException e) {
            fail(String.format(JSON_READ_ERROR, filePath));
        }

        return requestJson;
    }
}
