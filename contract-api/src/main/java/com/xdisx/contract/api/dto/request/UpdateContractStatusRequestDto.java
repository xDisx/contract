package com.xdisx.contract.api.dto.request;

import com.xdisx.contract.api.dto.ContractStatusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContractStatusRequestDto {
    private ContractStatusDto newStatus;
}
