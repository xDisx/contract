package com.xdisx.contract.app.mock;

import com.xdisx.contract.app.repository.db.dto.ContractPageDto;

import java.math.BigInteger;
import java.time.LocalDateTime;

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
    public String getContractType() {
        return "Type";
    }
}
