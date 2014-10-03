@addVenue
Feature: Add,Edit and delete a Venue in CMS
#As a an Editor
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


#  Scenario: Editor can able to add a new venue
#    Given Editor is logged-In and in DashBoardPage
#    When Editor selects Venues Option from DashBoardPage
#    Then Editor should be navigated to Venues Page
#    When Editor selects Add Venues Option
#    Then Editor should be navigate to Add Venues Page
#    When Editor selects the Language as 'British English'
#    And Editor enters the Name as 'Sri' and City as 'London'
#    And Editor selects the Site as 'UK - London'
#    And Saves the Venue
#    Then Editor should see the message as 'The venue was created successfully.'
#    And Editor should be navigate to the 'Edit venue' Page

#  @editVenue
#  Scenario: I can able to check the newly added venue in the venue list and edit the venue
#    Given I am on the Venues Page
#    When I search for the venue with the Name as 'Srikanth' and Site as 'UK - London'
#    And I selects UpdatedInLast as 'Week'
#    And I select the recently created Venue with the name 'Srikanth'
#    Then I should navigate to the Edit Venue Page
#    When I changes the BuildingNo as 'Sri-Building_01' and Author as 'Srikanth' and Status as 'Complete'
#    And I save the Venue
#    Then I should see the message as 'The venue was saved successfully.'
#
#
#  @deleteVenue
#  Scenario: I can able to check the newly added venue in the venue list and change the status
#    Given I am on the Venues Page
#    When I search for the venue with the Name as 'Srikanth' and Site as 'UK - London'
#    And I selects UpdatedInLast as 'Week'
#    And I select the recently created Venue with the name 'Srikanth'
#    Then I should navigate to the Edit Venue Page
#    When I changes the BuildingNo as 'Sri-Building_01' and Author as 'Srikanth' and Status as 'Deleted'
#    And I save the Venue
#    Then I should see the message as 'The venue was saved successfully.'


#@verifyvenue
#  Scenario: Editor can able to check the newly added venue in the venue list and delete the venue
#    Given Editor is in the Venues Page
#    When Editor selects the Name as 'Sri' and Site as 'UK - London'
#    And Searches for the Venue
#    Then Editor should see the list of venues
#    When Editor selects the recently created Venue with the name
#    Then Editor should navigate to the Edit Venue Page
#    When Editor changes the status as 'Deleted'
#    And selects the Save Option
#    Then Editor should see the message as 'The venue was saved successfully.'

