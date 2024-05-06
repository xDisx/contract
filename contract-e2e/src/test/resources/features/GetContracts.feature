Feature: Fetching contracts from db

  Scenario: Get first page of contracts
    When I request the first page of contracts
    Then I receive a page of contracts