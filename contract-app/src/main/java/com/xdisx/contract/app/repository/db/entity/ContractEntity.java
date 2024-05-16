package com.xdisx.contract.app.repository.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

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
    @Column(name = "customer_id")
    private BigInteger customerId;

    @NotNull
    @Column(name = "device_code")
    private String deviceCode;

    @NotNull
    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "product_id")
    private BigInteger productId;

    @NotNull
    @Column(name = "period")
    private Integer period;

    @NotNull
    @Column(name = "customer_name")
    private String customerName;

    @NotNull
    @Column(name = "acquisition_date")
    private LocalDate acquisitionDate;

    @NotNull
    @Column(name = "contract_status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;


    @Column(name = "contract_start_date")
    private LocalDate contractStartDate;

    @Column(name = "contract_planned_end_date")
    private LocalDate contractPlannedEndDate;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @Lob
    @Column(name = "contract_document")
    private byte[] contractDocument;

}
