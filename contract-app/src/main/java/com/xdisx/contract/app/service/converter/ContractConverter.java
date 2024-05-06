package com.xdisx.contract.app.service.converter;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.repository.db.dto.ContractPageDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ContractConverter {
    public  ContractEntity fromCreateRequest(ContractCreateRequestDto createRequestDto) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setContractType(createRequestDto.getContractType());

        return contractEntity;
    }

    public ContractResponseDto toContractResponse(ContractEntity contract) {
        return ContractResponseDto.builder().ID(contract.getId()).contractType(contract.getContractType()).build();
    }

    public PageRequest toPageRequest(ContractPageRequestDto contractPageRequest) {
        return PageRequest.of(
                contractPageRequest.getPageNumber(),
                contractPageRequest.getPageSize(),
                Sort.by(
                        Sort.Direction.valueOf(contractPageRequest.getOrderBy().toString()), contractPageRequest.getSortBy()));
    }

    public List<ContractResponseDto> toListContractResponse(Page<ContractPageDto> contractPageDto) {
        return contractPageDto.map(ContractConverter::toContractResponse).stream().toList();
    }

    private ContractResponseDto toContractResponse(ContractPageDto pageDto) {
        return ContractResponseDto.builder().ID(pageDto.getId()).contractType(pageDto.getContractType()).build();
    }
}
