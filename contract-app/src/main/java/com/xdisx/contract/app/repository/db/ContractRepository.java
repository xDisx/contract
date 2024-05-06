package com.xdisx.contract.app.repository.db;

import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, BigInteger>, JpaSpecificationExecutor<ContractEntity> {
}
