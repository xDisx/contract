Feature: Update contract status

  Scenario: Activate contract
    Given a contract with status CREATED exists in the system
    When I update the status to ACTIVE
    Then I receive the active contract