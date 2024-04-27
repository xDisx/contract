package com.xdisx.contract.e2e.contract.steps;

import com.xdisx.contract.e2e.CucumberBootstrap;
import com.xdisx.contract.e2e.contract.rest.ContractController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContractSteps extends CucumberBootstrap {

  private final ContractController contractController;

  @When("I call the test api")
  public void iCallTheTestApi() {
    String rez = contractController.salut(22);
    System.out.println("am primit rezultat " + rez);
  }

  @Then("I receive the doubled number")
  public void iReceiveTheDoubledNumber() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("am fost pe aici");
  }
}
