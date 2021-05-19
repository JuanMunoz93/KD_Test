Feature: Newsletter Subscription - Integration/E2E approach

  Background:
    Given I have an email with inbox
    When I open the Hoffner login page
    And I subscript my email to receive newsletter

  @Newsletter @Optional
  Scenario: Reception of newsletter subscription confirmation message
    Then I receive a mail to finish my subscription

  @Newsletter @Optional
  Scenario: Reception of link to finish newsletter subscription
    Then I receive a link in a mail to finish my subscription

  @Newsletter @Optional
  Scenario: Newsletter subscription confirmation
    When I complete my registration
    Then the confirming subscription page is opened in a new tab