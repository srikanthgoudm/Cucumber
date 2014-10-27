@addEvent
Feature: Add,Edit and delete an Event in CMS
#As a an Admin
#I want to see Event Page
#so that I can add edit and delete a Event

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new Event
    When I add a Event
    And I supply information
      | Language         | Name      | Site|
      | British English  | Test Event | UK - London |
    And I save it
    Then the Event is created and should see message as 'The event was created successfully.'
    And I should be navigate to the 'Edit event' Page
    When I add taxonomy for Event
    And I go back to Edit event Page
    And I change the Event status as 'Complete'
    And I save it
#    And I logout
#    Then I should redirect to Login Page

  @editanDeleteEvent
  Scenario Outline: I can able to find the newly added Events in the list and I can Edit and Delete
    Given I am on the Events Page
    When I search for the Event with the Name as 'Test Event' and Site as 'UK - London'
    And I select the status as 'Complete'
    And I select UpdatedInLast as 'Week'
    And I select the recently created Event with the name 'Test Event'
    Then I should be navigate to the 'Edit event' Page
    When I changes event url as 'http://www.testurl.com' and ticket url as 'http://www.ticketmaster.co.uk'
    And select editorial rating as '4', Author-name as 'Srikanth' and Status as '<Status>'
    And I save the Event
    Then I should see the message as 'The event was saved successfully.'
  Examples:
    |Status|
    |Complete|
    |Deleted |
