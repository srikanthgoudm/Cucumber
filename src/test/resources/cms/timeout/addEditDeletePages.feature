@addPage
Feature: Add Feature Page,Feature Package and Edit,Delete a Feature Page in CMS
#As a an Admin
#I want to see CMS Pages
#so that I can add edit and delete a Page

  Background:
    Given I am Logged-In

  Scenario Outline: I can able to add a new Page
    Given I am on the Pages Page
    When I add a Page,I supply '<Page Name>','<Page Title>','<Sub Title>','<Site>','<Language>','<Page Type>'
    And I save it
    Then the Page is created and should see message as 'The page was created successfully'
    And I should be navigate to the 'Edit page' Page
    When I add taxonomy for Page
    And I go back to Edit page
    And I change the Page status as 'Complete'
    And I save it
    Examples:
      |Page Name       |Page Title | Sub Title     | Site          | Language         | Page Type|
      |TestFeaturePage |Test Feature | Test Sub Title|UK - London    | British English  | Feature |
      |TestFeaturePage |Test Feature | Test Sub Title|US - Las Vegas | American English | Feature |
#      |TestFeaturePage |Test Feature | Test Sub Title|China - Shanghai | American English | Feature |
      |Test Package      |Test Title | Test Sub Title|UK - London    | British English  | Package |


  @editAndDeletePage
  Scenario Outline: I can able to find the newly added Pages in the list and I can Edit and Delete
    Given I am on the Pages Page
    When I search for the Page with Keyword 'Test Feature',Site as 'UK - London' and Status as 'Complete'
    And I select the recently created Page with the name 'Test Feature'
    Then I should be navigate to the 'Edit page' Page
    When I changes event Subtitle as '<subtitle>' and status as '<Status>'
    And I save the Page
    Then I should see the message as 'The page was updated successfully'
  Examples:
    |subtitle|Status|
    |Modified Test Sub Title|Complete |
    |Deleting This Page     |Deleted  |

#    @addEditDeleteZone
#    Scenario: I can able to add a New Zone to the existing Page
#      Given I am on the Pages Page
#      When I search for the Page with Keyword 'Test Feature',Site as 'UK - London' and Status as 'Complete'
#      And I select the recently created Page with the name 'Test Feature'
#      Then I should be navigate to the 'Edit page' Page