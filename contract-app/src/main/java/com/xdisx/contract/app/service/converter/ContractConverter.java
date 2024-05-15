package com.xdisx.contract.app.service.converter;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.DeviceTypeDto;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.repository.db.dto.ContractPageDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.contract.app.repository.db.entity.DeviceType;
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

        contractEntity.setCustomerId(createRequestDto.getCustomerId());
        contractEntity.setDeviceCode(createRequestDto.getDeviceCode());
        contractEntity.setDeviceType(DeviceType.valueOf(createRequestDto.getDeviceType().toString()));
        contractEntity.setPeriod(createRequestDto.getPeriod());
        contractEntity.setPrice(createRequestDto.getPrice());
        contractEntity.setProductId(createRequestDto.getProductId());
        contractEntity.setAcquisitionDate(createRequestDto.getAcquisitionDate());

        return contractEntity;
    }

    public ContractResponseDto toContractResponse(ContractEntity contract) {
        return ContractResponseDto.builder().ID(contract.getId())
                .customerId(contract.getCustomerId()).deviceCode(contract.getDeviceCode())
                .deviceType(DeviceTypeDto.valueOf(contract.getDeviceType().toString()))
                .period(contract.getPeriod())
                .price(contract.getPrice())
                .productId(contract.getProductId()).created(contract.getCreated()).customerName(contract.getCustomerName())
                .acquisitionDate(contract.getAcquisitionDate()).contractStatus(ContractStatusDto.valueOf(contract.getContractStatus().toString()))
                .contractStartDate(contract.getContractStartDate()).contractPlannedEndDate(contract.getContractPlannedEndDate())
                .productName(contract.getProductName()).build();
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
        return ContractResponseDto.builder().ID(pageDto.getId())    .customerId(pageDto.getCustomerId()).deviceCode(pageDto.getDeviceCode())
                .deviceType(DeviceTypeDto.valueOf(pageDto.getDeviceType().toString()))
                .period(pageDto.getPeriod())
                .price(pageDto.getPrice())
                .productId(pageDto.getProductId()).created(pageDto.getCreated()).customerName(pageDto.getCustomerName())
                .acquisitionDate(pageDto.getAcquisitionDate()).contractStatus(ContractStatusDto.valueOf(pageDto.getContractStatus().toString()))
                .contractStartDate(pageDto.getContractStartDate()).contractPlannedEndDate(pageDto.getContractPlannedEndDate())
                .productName(pageDto.getProductName()).build();
    }
}
