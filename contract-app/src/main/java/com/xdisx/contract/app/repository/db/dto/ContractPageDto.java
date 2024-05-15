package com.xdisx.contract.app.repository.db.dto;

import com.xdisx.contract.app.repository.db.entity.ContractStatus;
import com.xdisx.contract.app.repository.db.entity.DeviceType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ContractPageDto {
    BigInteger getId();
    LocalDateTime getCreated();
    LocalDateTime getModified();
    BigInteger getCustomerId();
    String getDeviceCode();
    DeviceType getDeviceType();
    BigDecimal getPrice();
    BigInteger getProductId();
    Integer getPeriod();
    String getCustomerName();
    LocalDate getAcquisitionDate();
    ContractStatus getContractStatus();
    LocalDate getContractStartDate();
    LocalDate getContractPlannedEndDate();
}
