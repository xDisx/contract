package com.xdisx.contract.api.dto.request;

import com.xdisx.contract.api.dto.DeviceTypeDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreateRequestDto {

    @NotNull(message = "Customer id must not be null!")
    private BigInteger customerId;

    @NotNull
    private String deviceCode;

    @NotNull
    private DeviceTypeDto deviceType;

    @NotNull
    private Integer period;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigInteger productId;

    @NotNull
    private LocalDate acquisitionDate;
}
