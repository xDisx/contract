package com.xdisx.contract.app.repository.db.filtering;

import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.app.repository.db.entity.ContractEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ContractSpecificationBuilder {

    public Specification<ContractEntity> getFilterSpecifications(ContractPageRequestDto contractPageRequest) {
        List<Specification<ContractEntity>> specifications = new ArrayList<>();

        if (contractPageRequest.getCreatedOn() != null) {
            specifications.add(buildEqualSpecificationCreatedOn(contractPageRequest.getCreatedOn()));
        }

        return Specification.allOf(specifications);
    }

    private static Specification<ContractEntity> buildEqualSpecificationCreatedOn(Object value) {
        LocalDateTime localDate = ((LocalDate) value).atStartOfDay();
        LocalDateTime localDate1 = localDate.plusDays(1).minusNanos(1);
        return (root, query, builder) ->
                builder.between(root.get("created"), localDate, localDate1);
    }

}
