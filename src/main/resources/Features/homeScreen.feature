@demo
Feature: Sample feature to demonstrate

  @negative
  Scenario: RedBus homepage loads properly
    Given I am on RedBus homePage
    And Validate if the home page loads fine
    Then Click on Search button
    And Validate error toast message
