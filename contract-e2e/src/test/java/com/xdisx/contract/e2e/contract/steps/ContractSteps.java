package com.xdisx.contract.e2e.contract.steps;

import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.request.UpdateContractStatusRequestDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.e2e.CucumberBootstrap;
import com.xdisx.contract.e2e.common.utils.AssertionsUtils;
import com.xdisx.contract.e2e.contract.rest.ContractController;
import com.xdisx.contract.e2e.contract.steps.context.GetContractContext;
import com.xdisx.contract.e2e.contract.steps.context.GetContractsContext;
import com.xdisx.contract.e2e.contract.steps.context.UpdateStatusContext;
import com.xdisx.contract.e2e.contract.steps.service.RequestBuilderServiceContract;
import feign.FeignException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public class ContractSteps extends CucumberBootstrap {

  private final RequestBuilderServiceContract requestBuilder;
  private GetContractsContext getContractsContext;
  private GetContractContext getContractContext;
  private UpdateStatusContext updateStatusContext;

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

  @Given("a contract with status CREATED exists in the system")
  public void aContractWithStatusCREATEDExistsInTheSystem() {
      contractCreationContext.setResponse(fetchOrCreateAContract());
  }

  private ContractResponseDto fetchOrCreateAContract() {
      var contractsRequest = ContractPageRequestDto.builder().contractStatus(ContractStatusDto.CREATED).build();
      var contracts = contractController.getContracts(contractsRequest);

      return contracts.getContracts().isEmpty() ? createContract() : contracts.getContracts().get(0);


  }

  private ContractResponseDto createContract() {
    var createContractRequest = requestBuilder.buildContractCreateRequest();

    return contractController.createContract(createContractRequest);
  }

  @When("I request the details of the contract")
    public void iRequestTheDetailsOfTheContract() {
      getContractContext = new GetContractContext();
      try{
        getContractContext.setResponseDto(contractController.getContract(contractCreationContext.getResponse().getID()));
        getContractContext.setStatus(OK.value());
      } catch (FeignException e) {
        getContractContext.setException(e);
      }

    }


  @Then("I receive the contract")
  public void iReceiveTheContract() {
      AssertionsUtils.assertAPISuccess(getContractContext);
      var contractResponse = getContractContext.getResponseDto();

      assertNotNull(contractResponse);
  }

  @When("I update the status to ACTIVE")
  public void iUpdateTheStatusToACTIVE() {
      updateStatusContext = new UpdateStatusContext();
      var contract = contractCreationContext.getResponse();

      try{
        updateStatusContext.setResponseDto( contractController.updateContractStatus(contract.getID(), UpdateContractStatusRequestDto.builder().newStatus(ContractStatusDto.ACTIVE).build()));
updateStatusContext.setStatus(OK.value());
      } catch (FeignException e) {
        updateStatusContext.setException(e);
      }
  }

  @Then("I receive the active contract")
  public void iReceiveTheActiveContract() {
      AssertionsUtils.assertAPISuccess(updateStatusContext);
      var contractResponse = updateStatusContext.getResponseDto();
      assertNotNull(contractResponse);
      assertEquals(ContractStatusDto.ACTIVE, contractResponse.getContractStatus());
  }
}
