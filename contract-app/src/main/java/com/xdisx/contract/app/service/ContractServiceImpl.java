package com.xdisx.contract.app.service;

import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.api.exception.ContractCreateException;
import com.xdisx.contract.app.repository.db.ContractRepository;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import com.xdisx.contract.app.service.converter.ContractConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    public static final String CONTRACT_SAVE_ERROR = "Unable to save contract";
    private final ContractRepository contractRepository;

    @Override
    @Transactional
    public ContractResponseDto createContract(ContractCreateRequestDto contractCreateRequest) {
        ContractEntity contract = ContractConverter.fromCreateRequest(contractCreateRequest);

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
