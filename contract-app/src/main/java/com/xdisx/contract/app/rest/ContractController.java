package com.xdisx.contract.app.rest;

import com.xdisx.contract.api.ContractApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController implements ContractApi {
    @Override
    public String salut(Integer numar) {
        log.info("am primit API call cu " + numar);
        Integer nr2 = numar * 2;
        return nr2.toString();
    }
}
