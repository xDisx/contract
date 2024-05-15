package com.xdisx.contract.app.repository.customer;

import com.xdisx.contract.app.config.error_decoder.FeignErrorDecoder;
import com.xdisx.customer.api.CustomerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${spring.xdisx.contract.customer.client-name}",
        url = "${xdisx.contract.customer.url}",
        configuration = FeignErrorDecoder.class)
public interface CustomerRepository extends CustomerApi {}
