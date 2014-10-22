@addPage
Feature: Add,Edit and delete an Page in CMS
#As a an Admin
#I want to see CMS Pages
#so that I can add edit and delete a Page

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new Page
    Given I am on the Pages Page
    When I add a Page
    And I supply the Page information
      |Page Title | Sub Title     | Site       | Language         | Page Type|
      |Test Title | Test Sub Title|UK - London | British English  | Feature |
    And I save it
    Then the Page is created and should see message as 'The page was created successfully'
    And I should be navigate to the 'Edit page' Page
    When I add taxonomy for Page
    And I go back to Edit page
    And I change the Page status as 'Complete'
    And I save it


  @editAndDeletePage
  Scenario Outline: I can able to find the newly added Pages in the list and I can Edit and Delete
    Given I am on the Pages Page
    When I search for the Page Site as 'UK - London'
    And I select the recently created Page with the name 'Test Title'
    Then I should be navigate to the 'Edit page' Page
    When I changes event Subtitle as 'Modified Test Sub Title' and status as '<Status>'
    And I save the Page
    Then I should see the message as 'The page was updated successfully'
  Examples:
    |Status|
    |Complete |
    |Deleted  |