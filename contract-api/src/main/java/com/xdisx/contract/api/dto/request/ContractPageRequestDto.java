package com.xdisx.contract.api.dto.request;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.OrderByDirection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractPageRequestDto {
    @NotNull(message = "Page number must not be null!") @Min(value = 0, message = "Invalid page number, must be greater than or equals 0!")
    @Builder.Default
    private Integer pageNumber = 0;

    @NotNull(message = "Page size must not be null!") @Min(value = 10, message = "Invalid page size, must be greater than or equals 10!")
    @Max(value = 150, message = "Invalid page size, must be less than or equals 50!")
    @Builder.Default
    private Integer pageSize = 10;

    @NotNull(message = "Sort by must not be null!") @Builder.Default
    private String sortBy = "modified";

    @NotNull(message = "Order by must not be null!") @Builder.Default
    private OrderByDirection orderBy = OrderByDirection.DESC;

    private LocalDate createdOn;
    private BigInteger customerId;
    private ContractStatusDto contractStatus;
    private String customerName;
    private String deviceCode;
}
