Feature: Newsletter Subscription

  Background:
    Given I have an email with inbox
    When I open the Hoffner login page
    And I subscript my email to receive newsletter

  Scenario: Visibility of newsletter subscription confirmation message
    Then I receive and email to finish my subscription

  Scenario: Complete newsletter subscription
    When I complete my registration
    Then the confirming subscription page is opened in a new tab