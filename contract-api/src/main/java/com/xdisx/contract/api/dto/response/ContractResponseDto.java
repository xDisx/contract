package com.xdisx.contract.api.dto.response;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.DeviceTypeDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDto {
    private BigInteger ID;
    private BigInteger customerId;
    private String deviceCode;
    private DeviceTypeDto deviceType;
    private Integer period;
    private BigDecimal price;
    private BigInteger productId;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime created;
    private String customerName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate acquisitionDate;
    private ContractStatusDto contractStatus;
    private LocalDate contractStartDate;
    private LocalDate contractPlannedEndDate;
    private String productName;
}
