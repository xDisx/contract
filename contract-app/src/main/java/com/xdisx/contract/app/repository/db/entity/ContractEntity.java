package com.xdisx.contract.app.repository.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

@Entity
@Table(name = "contracts")
@Getter
@Setter
public class ContractEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_gen")
    @SequenceGenerator(name = "contract_id_gen", sequenceName = "contract_id_seq", allocationSize = 1)
    private BigInteger id;

    @NotNull
    @Column(name = "contract_type")
    private String contractType;
}
