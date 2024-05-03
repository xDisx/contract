package com.xdisx.contract.app.service.converter;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContractConverter {
    public static ContractEntity fromCreateRequest(ContractCreateRequestDto createRequestDto) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setContractType(createRequestDto.getContractType());

        return contractEntity;
    }

    public static ContractResponseDto toContractResponse(ContractEntity contract) {
        return ContractResponseDto.builder().ID(contract.getId()).contractType(contract.getContractType()).build();
    }
}
