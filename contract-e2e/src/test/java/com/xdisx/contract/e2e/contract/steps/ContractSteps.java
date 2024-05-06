package com.xdisx.contract.e2e.contract.steps;

import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.e2e.CucumberBootstrap;
import com.xdisx.contract.e2e.common.utils.AssertionsUtils;
import com.xdisx.contract.e2e.contract.rest.ContractController;
import com.xdisx.contract.e2e.contract.steps.context.GetContractsContext;
import com.xdisx.contract.e2e.contract.steps.service.RequestBuilderServiceContract;
import feign.FeignException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public class ContractSteps extends CucumberBootstrap {

  private final RequestBuilderServiceContract requestBuilder;
  private GetContractsContext getContractsContext;

  @Before
  public void setup() {
    contractCreationContext.reset();
  }

  private final ContractController contractController;

    @When("I create a new contract")
    public void iCreateANewContract() {
        var createContractRequest = requestBuilder.buildContractCreateRequest();

        try{
          contractCreationContext.setResponse(contractController.createContract(createContractRequest));
          contractCreationContext.setStatus(OK.value());
        } catch (FeignException e) {
          contractCreationContext.setStatus(e.status());
          contractCreationContext.setException(e);
        }

    }

  @Then("I receive the created contract")
  public void iReceiveTheCreatedContract() {
    AssertionsUtils.assertAPISuccess(contractCreationContext);

    var contractResponse = contractCreationContext.getResponse();
    assertNotNull(contractResponse);
  }

    @When("I request the first page of contracts")
    public void iRequestTheFirstPageOfContracts() {
      var getContractsRequest = ContractPageRequestDto.builder().build();
      getContractsContext = new GetContractsContext();
      try {
        getContractsContext.setContractPageResponseDto(contractController.getContracts(getContractsRequest));
        getContractsContext.setStatus(OK.value());
      } catch (FeignException e) {
        getContractsContext.setException(e);
      }


    }

  @Then("I receive a page of contracts")
  public void iReceiveAPageOfContracts() {
      AssertionsUtils.assertAPISuccess(getContractsContext);
      var response = getContractsContext.getContractPageResponseDto();
      assertNotNull(response);
  }
}
