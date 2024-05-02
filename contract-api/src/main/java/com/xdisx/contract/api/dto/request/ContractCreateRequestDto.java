package com.xdisx.contract.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreateRequestDto {
    @NotBlank(message = "Contract type must not be blank!")
    private String contractType;

    @NotNull(message = "Customer id must not be null!")
    private BigInteger customerId;
}
