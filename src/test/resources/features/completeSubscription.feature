Feature: Newsletter Subscription

  Background:
    Given I have a email with inbox

  Scenario: Visibility of newsletter subscription confirmation message
    When I open the Hoffner login page
    And I subscript my email to receive newsletter
    Then I receive and email to finish my subscription
