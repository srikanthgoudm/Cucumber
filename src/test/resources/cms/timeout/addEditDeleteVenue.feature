@addVenue
Feature: Add,Edit and delete a Venue in CMS
#As a an Admin
#I want to see Venue Page
#so that I can add edit and delete a Venue

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new venue
    When I add a Venue
    And I supply the information
      | Site        | Language         | Name     | City   |
      | UK - London | British English  | Srikanth | London |
    And I save it
    Then the Venue is created and should see message as 'The venue was created successfully.'
    And I should be navigate to the 'Edit venue' Page
    When I add taxonomy for Venue
    And I go back to Edit Venue Page
    And I change the Venue status as 'Complete'
    And I save it
#    And I logout
#    Then I should redirect to Login Page

  @editanddeleteVenue
  Scenario Outline: I can able to find the newly added venue in the list and I can Edit and Delete
    Given I am on the Venues Page
    When I search for the venue with the Name as 'Srikanth' and Site as 'UK - London'
    And I selects UpdatedInLast as 'Week'
    And I select the recently created Venue with the name 'Srikanth'
    Then I should be navigate to the 'Edit venue' Page
    When I changes the BuildingNo as 'Sri-Building_01' and Author as 'Srikanth' and Status as '<Status>'
    And I save the Venue
    Then I should see the message as 'The venue was saved successfully.'
  Examples:
  |Status|
  |Complete|
  |Deleted |