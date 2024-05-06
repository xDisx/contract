package com.xdisx.contract.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractPageResponseDto {
    private int totalPages;
    private long totalElements;
    private List<ContractResponseDto> contracts;
}
