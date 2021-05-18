Feature: Newsletter Subscription

  Background:
    Given a Hoffner login page

  Scenario: Visibility of newsletter subscription field
    When I open a page
    Then I can see a newsletter subscription input

  Scenario: Visibility of newsletter subscription confirmation message
    When I enter my email in the input field
    And I press 'Absenden' button
    Then I can see a confirmation message that my subscription is in progress