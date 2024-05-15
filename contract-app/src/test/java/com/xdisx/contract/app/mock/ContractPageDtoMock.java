package com.xdisx.contract.app.mock;

import com.xdisx.contract.app.repository.db.dto.ContractPageDto;
import com.xdisx.contract.app.repository.db.entity.ContractStatus;
import com.xdisx.contract.app.repository.db.entity.DeviceType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.xdisx.contract.app.mock.ContractMock.*;

public class ContractPageDtoMock implements ContractPageDto {
    @Override
    public BigInteger getId() {
        return BigInteger.ONE;
    }

    @Override
    public LocalDateTime getCreated() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDateTime getModified() {
        return LocalDateTime.now();
    }

    @Override
    public BigInteger getCustomerId() {
        return CUSTOMER_ID;
    }

    @Override
    public String getDeviceCode() {
        return DEVICE_CODE;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.valueOf(DEVICE_TYPE.toString());
    }

    @Override
    public BigDecimal getPrice() {
        return PRICE;
    }

    @Override
    public BigInteger getProductId() {
        return PRODUCT_ID;
    }

    @Override
    public Integer getPeriod() {
        return PERIOD;
    }

    @Override
    public String getCustomerName() {
        return CUSTOMER_NAME;
    }

    @Override
    public LocalDate getAcquisitionDate() {
        return ACQUISITION_DATE;
    }

    @Override
    public ContractStatus getContractStatus() {
        return ContractStatus.CREATED;
    }

    @Override
    public LocalDate getContractStartDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDate getContractPlannedEndDate() {
        return LocalDate.now().plusYears(1);
    }

    @Override
    public String getProductName() {
        return "Basic assurance";
    }
}
