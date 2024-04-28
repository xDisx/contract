package com.xdisx.contract.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreateRequestDto {
    @NotBlank(message = "Contract type must not be blank!")
    private String contractType;
}
