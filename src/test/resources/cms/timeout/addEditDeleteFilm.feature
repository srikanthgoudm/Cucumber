@addFilm
Feature: Add,Edit and delete a Film in CMS
#As a an Editor
#I want to see Film Page
#so that I can add edit and delete Film

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new Film
    When I add a Film
    And I supply the Film information
      | Language        | Original Title | Title      | Author   | Editorial Rating|
      | British English | Test Film      | Test Title | Srikanth | 3               |
    And I save it
    Then the Film is created and should see message as 'The film was created successfully.'
    And I should be navigate to the 'Edit film' Page
    When I add taxonomy for Film
    And I go back to Edit Film Page
    And I change the Film status as 'Complete'
    And I save it

  @editanddeleteFilm
  Scenario Outline: I can able to find the newly added film in the list and I can Edit and Delete
    Given I am on the Films Page
    When I search for the Film 'Test Film'
    And I select the recently created Film 'Test Film'
    Then I should be navigate to the 'Edit film' Page
    When I changes the Short Desc as 'Test Short Desc' and Editor rating as '5' and Author as 'Srikanth' and Status as '<Status>'
    And I save the Film
    Then I should see the message as 'The film was saved successfully.'
  Examples:
    |Status|
    |Complete|
    |Deleted |