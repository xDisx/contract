package com.xdisx.contract.e2e.contract.steps.context;

import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.e2e.common.context.ValidatedContext;
import feign.FeignException;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@Data
public class UpdateStatusContext implements ValidatedContext {
    private int status;
    private FeignException exception;
    private ContractResponseDto responseDto;
}
