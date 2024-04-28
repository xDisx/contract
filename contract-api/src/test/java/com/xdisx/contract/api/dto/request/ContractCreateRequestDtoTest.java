package com.xdisx.contract.api.dto.request;

import com.xdisx.contract.api.utils.ObjectDeserializerUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContractCreateRequestDtoTest {

    private final ObjectDeserializerUtils utils = new ObjectDeserializerUtils();

    @Test
    void deserializeCreateContractRequest() throws JSONException, IOException {
        utils.assertSerialization(
                "ContractCreateRequest.json", ContractCreateRequestDto.class);
    }
}
