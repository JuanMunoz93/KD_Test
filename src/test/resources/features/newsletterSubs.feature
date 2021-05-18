Feature: Newsletter subscription section

  Background:
    Given a Hoffner login page
    When I open a page

  Scenario: Visibility of newsletter subscription field
    Then I can see a newsletter subscription input

  Scenario: Visibility of newsletter subscription confirmation message
    When I enter a email in the input field
    And I press 'Absenden' button
    Then I can see a confirmation message that my subscription is in progress