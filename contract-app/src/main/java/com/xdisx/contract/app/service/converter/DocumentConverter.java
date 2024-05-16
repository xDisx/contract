package com.xdisx.contract.app.service.converter;

import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.customer.api.dto.response.CustomerResponseDto;
import com.xdisx.document.api.dto.request.DocumentRequestDto;
import com.xdisx.product.api.dto.response.ProductResponseDto;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class DocumentConverter {
    public static DocumentRequestDto toDocumentRequest(ContractEntity contract, CustomerResponseDto customer, ProductResponseDto product) {
        DocumentRequestDto documentRequestDto = new DocumentRequestDto();
        Map<String, Object> data = new HashMap<>();

        data.put("contractId", contract.getId());
        data.put("deviceCode", contract.getDeviceCode());
        data.put("deviceType", contract.getDeviceType());
        data.put("deviceAcquisitionDate", contract.getAcquisitionDate());
        data.put("contractPrice", contract.getPrice());
        data.put("contractPeriod", contract.getPeriod());
        data.put("customerFirstName", customer.getFirstName());
        data.put("customerLastName", customer.getLastName());
        data.put("customerEmail", customer.getEmail());
        data.put("customerPhoneNumber", customer.getPhoneNumber());
        data.put("customerAddress", customer.getAddress());
        data.put("contractStartDate", contract.getContractStartDate());
        data.put("contractPlannedEndDate", contract.getContractPlannedEndDate());
        data.put("productName", product.getProductName());
        data.put("productDescription", product.getDescription());

        documentRequestDto.setData(data);

        return documentRequestDto;
    }
}
