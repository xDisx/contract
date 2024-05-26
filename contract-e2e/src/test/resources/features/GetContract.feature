Feature: Get a contract

  Scenario: Fetching a contract
    Given a contract with status CREATED exists in the system
    When I request the details of the contract
    Then I receive the contract