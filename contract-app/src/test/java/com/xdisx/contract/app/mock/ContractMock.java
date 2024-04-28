package com.xdisx.contract.app.mock;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;

@UtilityClass
public class ContractMock {
    private static final String CONTRACT_TYPE = "New Contract";
  private static final BigInteger CONTRACT_ID = BigInteger.ONE;

    public static ContractCreateRequestDto getCreateContractRequest() {
        return ContractCreateRequestDto.builder().contractType(CONTRACT_TYPE).build();
    }

    public static ContractResponseDto getContractResponse() {
        return ContractResponseDto.builder().contractType(CONTRACT_TYPE).ID(CONTRACT_ID).build();
    }

    public static ContractEntity getContractEntity(ContractCreateRequestDto requestDto) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setContractType(requestDto.getContractType());
        contractEntity.setId(CONTRACT_ID);

        return contractEntity;
    }
}
