
Feature: Newsletter subscription section - functional approach

  Background:
    Given a Hoffner login page
    When I open a page

  @Cucumber @Newsletter @Mandatory
  Scenario: Visibility of newsletter subscription field
    Then I can see a newsletter subscription input

  @Cucumber @Newsletter @Mandatory
  Scenario: Visibility of newsletter subscription confirmation message
    When I enter a email in the input field
    And I press 'Absenden' button
    Then I can see a confirmation message that my subscription is in progress