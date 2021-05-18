Feature: Newsletter Subscription

  Background:
    Given I have a mail with inbox

  Scenario: Visibility of newsletter subscription confirmation message
    When I open the Hoffner login page
    When I enter a email in the input field
    And I press 'Absenden' button
    Then I can see a confirmation message that my subscription is in progress
