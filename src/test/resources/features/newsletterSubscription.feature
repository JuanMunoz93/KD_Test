Feature: Newsletter Subscription

  Background:
    Given I open the Hoffner login page

  Scenario: Visibility of newsletter subscription confirmation message
    When I enter a email in the input field
    And I press 'Absenden' button
    Then I can see a confirmation message that my subscription is in progress