package com.xdisx.contract.api.dto.response;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.utils.ObjectDeserializerUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ContractResponseDtoTest {

    private final ObjectDeserializerUtils utils = new ObjectDeserializerUtils();

    @Test
    void deserializeContractResponse() throws JSONException, IOException {
        utils.assertSerialization(
                "ContractResponse.json", ContractResponseDto.class);
    }
}
