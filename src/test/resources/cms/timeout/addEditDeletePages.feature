@addPage
Feature: Add Feature Page,Feature Package and Edit,Delete a Feature Page in CMS
#As a an Admin
#I want to see CMS Pages
#so that I can add edit and delete a Page

  Background:
    Given I am Logged-In

  Scenario Outline: I can able to add a new Feature Page and Feature Package
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
      |TestFeaturePageLondon|LondonFeaturePage |TestSubTitle|UK - London| British English  | Feature |
      |TestFeaturePackageLondon|LondonFeaturePackage |TestSubTitle|UK - London| British English  | Package |
      |TestFeaturePageSeoul|SeoulFeaturePage|TestSubTitle|South Korea - Seoul|American English|Feature|
      |TestFeaturePackageSeoul|SeoulFeaturePackage|TestSubTitle|South Korea - Seoul|American English|Package|
      |TestFeaturePageAtlanta|AtlantaFeaturePage |TestSubTitle|US - Atlanta | American English | Feature |
      |TestFeaturePackageAtlanta|AtlantaFeaturePackage|TestSubTitle|US - Atlanta | American English | Package |



  @editAndDeletePage
  Scenario Outline: I can able to find the newly added Pages in the list and I can Edit and Delete
    Given I am on the Pages Page
    When I search for the Page with Keyword '<Page Title>',Site as '<Site>' and Status as 'Complete'
    And I select the recently created Page with the name '<Page Title>'
    Then I should be navigate to the 'Edit page' Page
    When I changes event Subtitle as '<subtitle>' and status as '<Status>'
    And I save the Page
    Then I should see the message as 'The page was updated successfully'
  Examples:
    |Page Title|Site|subtitle|Status|
    |LondonFeaturePage|UK - London|Modified Test Sub Title|Complete |
    |LondonFeaturePage|UK - London|Modified Test Sub Title|Complete |
    |SeoulFeaturePage|South Korea - Seoul|Deleting This Page|Complete |
    |SeoulFeaturePage|South Korea - Seoul|Deleting This Page|Deleted  |

#    @addEditDeleteZone
#    Scenario: I can able to add a New Zone to the existing Page
#      Given I am on the Pages Page
#      When I search for the Page with Keyword 'Test Feature',Site as 'UK - London' and Status as 'Complete'
#      And I select the recently created Page with the name 'Test Feature'
#      Then I should be navigate to the 'Edit page' Page