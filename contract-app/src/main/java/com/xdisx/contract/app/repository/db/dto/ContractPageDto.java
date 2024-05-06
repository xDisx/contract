package com.xdisx.contract.app.repository.db.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface ContractPageDto {
    BigInteger getId();
    LocalDateTime getCreated();
    LocalDateTime getModified();
    String getContractType();
}
