package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.request.UpdateContractStatusRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.api.exception.ContractCreateException;
import com.xdisx.contract.api.exception.ContractNotFoundException;
import com.xdisx.contract.app.repository.customer.CustomerRepository;
import com.xdisx.contract.app.repository.db.ContractRepository;
import com.xdisx.contract.app.repository.db.dto.ContractPageDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.contract.app.repository.db.entity.ContractStatus;
import com.xdisx.contract.app.repository.db.filtering.ContractSpecificationBuilder;
import com.xdisx.contract.app.repository.product.ProductRepository;
import com.xdisx.contract.app.service.converter.ContractConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    public static final String CONTRACT_SAVE_ERROR = "Unable to save contract";
    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ContractResponseDto createContract(ContractCreateRequestDto contractCreateRequest) {
        ContractEntity contract = ContractConverter.fromCreateRequest(contractCreateRequest);

        var customer = customerRepository.getCustomer(contract.getCustomerId());
        var product = productRepository.getProduct(contract.getProductId());

        contract.setCustomerName(String.format("%s %s", customer.getFirstName(), customer.getLastName()));
        contract.setProductName(product.getProductName());

        contract.setContractStatus(ContractStatus.CREATED);

        contract = saveAndFlushContract(contract);
        return ContractConverter.toContractResponse(contract);
    }

    @Override
    public ContractPageResponseDto findContracts(ContractPageRequestDto contractPageRequest) {
        PageRequest pageRequest =
                ContractConverter.toPageRequest(contractPageRequest);
        Specification<ContractEntity> specifications =
                ContractSpecificationBuilder.getFilterSpecifications(contractPageRequest);

        Page<ContractPageDto> result =
                contractRepository.findBy(
                        specifications,
                        q -> q.as(ContractPageDto.class).sortBy(pageRequest.getSort()).page(pageRequest));

        return new ContractPageResponseDto(
                result.getTotalPages(),
                result.getTotalElements(),
                ContractConverter.toListContractResponse(result));
    }

    @Override
    public ContractResponseDto getContract(BigInteger id) {
        ContractEntity contract =
                contractRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ContractNotFoundException(
                                                String.format("There is no contract with id %d", id)));
        return ContractConverter.toContractResponse(contract);
    }

    @Override
    public ContractResponseDto updateContractStatus(BigInteger id, UpdateContractStatusRequestDto updateContractStatusRequest) {
        ContractEntity contract =
                contractRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ContractNotFoundException(
                                                String.format("There is no contract with id %d", id)));

        contract.setContractStatus(ContractStatus.valueOf(updateContractStatusRequest.getNewStatus().toString()));
        if (ContractStatusDto.ACTIVE.equals(updateContractStatusRequest.getNewStatus())) {
            contract.setContractStartDate(LocalDate.now());
            contract.setContractPlannedEndDate(LocalDate.now().plusYears(contract.getPeriod()));
        }
        contract = saveAndFlushContract(contract);

        return ContractConverter.toContractResponse(contract);
    }

    private ContractEntity saveAndFlushContract(ContractEntity contract) {
        try {
            return contractRepository.saveAndFlush(contract);
        } catch (DataIntegrityViolationException e) {
            log.error("Unable to save contract with values {}", contract, e);
            throw new ContractCreateException(CONTRACT_SAVE_ERROR);
        }
    }
}
