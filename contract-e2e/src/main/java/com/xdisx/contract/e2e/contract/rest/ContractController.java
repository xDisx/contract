package com.xdisx.contract.e2e.contract.rest;

import com.xdisx.contract.api.ContractApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        contextId = "contractService",
        name = "${xdisx.service.contract.client-name}",
        url = "${xdisx.service.contract.url}")
public interface ContractController extends ContractApi {}
