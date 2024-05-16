package com.xdisx.contract.app.repository.document;

import com.xdisx.contract.app.config.error_decoder.FeignErrorDecoder;
import com.xdisx.document.api.DocumentApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${spring.xdisx.contract.document.client-name}",
        url = "${xdisx.contract.document.url}",
        configuration = FeignErrorDecoder.class)
public interface DocumentRepository extends DocumentApi {}
