package com.xdisx.contract.e2e.common.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.OK;
import com.xdisx.contract.e2e.common.context.ValidatedContext;

@UtilityClass
@Slf4j
public class AssertionsUtils {
    public void assertAPISuccess(ValidatedContext context) {
        if (OK.value() != context.getStatus()) {
            var ex = context.getException();
            log.error("Unexpected API call failure", ex);
            fail(String.format("Tested API call failed with error %s", ex.getMessage()));
        }
    }

}
