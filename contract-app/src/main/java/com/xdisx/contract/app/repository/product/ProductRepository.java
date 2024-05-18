package com.xdisx.contract.app.repository.product;

import com.xdisx.contract.app.config.error_decoder.FeignErrorDecoder;
import com.xdisx.contract.app.repository.config.FeignConfig;
import com.xdisx.product.api.ProductApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${spring.xdisx.contract.product.client-name}",
        url = "${xdisx.contract.product.url}",
        configuration = {FeignErrorDecoder.class, FeignConfig.class})
public interface ProductRepository extends ProductApi {}
