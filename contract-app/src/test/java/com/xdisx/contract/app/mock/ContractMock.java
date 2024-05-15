package com.xdisx.contract.app.mock;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.DeviceTypeDto;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.contract.app.repository.db.entity.ContractStatus;
import com.xdisx.contract.app.repository.db.entity.DeviceType;
import com.xdisx.customer.api.dto.response.CustomerResponseDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class ContractMock {
  public static final BigInteger CONTRACT_ID = BigInteger.ONE;
  public static final BigInteger CUSTOMER_ID = BigInteger.ONE;
  public static final String DEVICE_CODE = "12345xyz";
  public static final String CUSTOMER_NAME = "Bob";
  public static final DeviceTypeDto DEVICE_TYPE = DeviceTypeDto.LAPTOP;
  public static final Integer PERIOD = 2;
  public static final BigDecimal PRICE = BigDecimal.ONE;
  public static final BigInteger PRODUCT_ID = BigInteger.TEN;
  public static final LocalDateTime CREATED = LocalDateTime.now();
  public static final LocalDate ACQUISITION_DATE = LocalDate.now();
  private static final ContractStatusDto CONTRACT_STATUS = ContractStatusDto.CREATED;

  public static ContractCreateRequestDto getCreateContractRequest() {
    return ContractCreateRequestDto.builder()
        .customerId(CUSTOMER_ID)
        .deviceCode(DEVICE_CODE)
        .deviceType(DEVICE_TYPE)
        .period(PERIOD)
        .price(PRICE)
        .productId(PRODUCT_ID)
            .acquisitionDate(LocalDate.now())
        .build();
  }

  public static ContractResponseDto getContractResponse() {
    return ContractResponseDto.builder()
        .ID(CONTRACT_ID)
        .customerId(CUSTOMER_ID)
        .deviceCode(DEVICE_CODE)
        .deviceType(DEVICE_TYPE)
        .period(PERIOD)
        .price(PRICE)
        .productId(PRODUCT_ID)
        .created(CREATED)
        .contractStatus(CONTRACT_STATUS)
        .build();
  }

  public static ContractEntity getContractEntity() {
    ContractEntity contractEntity = new ContractEntity();
    contractEntity.setId(CONTRACT_ID);
    contractEntity.setProductId(PRODUCT_ID);
    contractEntity.setCustomerId(CUSTOMER_ID);
    contractEntity.setDeviceCode(DEVICE_CODE);
    contractEntity.setPrice(PRICE);
    contractEntity.setPeriod(PERIOD);
    contractEntity.setDeviceType(DeviceType.valueOf(DEVICE_TYPE.toString()));
    contractEntity.setContractStatus(ContractStatus.CREATED);
    contractEntity.setCreated(CREATED);

    return contractEntity;
  }

  public static ContractPageResponseDto getContractPageResponse() {
    ContractPageResponseDto contractPageResponseDto = new ContractPageResponseDto();
    contractPageResponseDto.setTotalPages(1);
    contractPageResponseDto.setTotalElements(1);
    contractPageResponseDto.setContracts(List.of(getContractResponse()));

    return contractPageResponseDto;
  }

  public static CustomerResponseDto getCustomerResponse() {
    return CustomerResponseDto.builder().ID(BigInteger.TWO).firstName("Bob").lastName("Bobb").build();
  }
}
