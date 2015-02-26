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
    Then I should see the message as 'The page was updated successfully'
    Examples:
      |Page Name       |Page Title | Sub Title     | Site          | Language         | Page Type|
#      |TestFeaturePageLondon|LondonFeaturePage |TestSubTitle|UK - London| British English  | Feature |
#      |TestHubPageLondon|LondonHubPage |TestSubTitle|UK - London| British English  | Hub |
#      |TestFeaturePackageLondon|LondonFeaturePackage |TestSubTitle|UK - London| British English  | Package |
#      |TestFeaturePageAtlanta|AtlantaFeaturePage |TestSubTitle|US - Atlanta |American English| Feature |
#      |TestHubPageAtlanta|AtlantaHubPage |TestSubTitle|US - Atlanta |American English| Hub |
#      |TestFeaturePackageAtlanta|AtlantaFeaturePackage|TestSubTitle|US - Atlanta | American English | Package |
#     |TestFeaturePageSeoul|SeoulFeaturePage|TestSubTitle|South Korea - Seoul|American English|Feature|
#      |TestFeaturePackageSeoul|SeoulFeaturePackage|TestSubTitle|South Korea - Seoul|American English|Package|
#      |TestFeaturePageTokyo|TokyoFeaturePage |TestSubTitle|Japan - Tokyo | American English | Feature |
#      |TestFeaturePageTokyo|TokyoFeaturePage |TestSubTitle|Japan - Tokyo | American English | Package |
#      |TestFeaturePageNashville|NashvilleFeaturePage |TestSubTitle|US - Nashville |American English| Feature |
#      |TestFeaturePackageNashville|NashvilleFeaturePackage|TestSubTitle|US - Nashville | American English | Package |
#      |TestHubPageNashville|NashvilleHubPage|TestSubTitle|US - Nashville | American English | Hub |
#      |TestHubPageCroatia|CroatiaHubPage |TestSubTitle|Croatia| British English  | Hub |
#      |TestFeaturePageCroatia|CroatiaFeaturePage |TestSubTitle|Croatia| British English  | Feature |
#      |TestFeaturePackageCroatia|CroatiaFeaturePackage |TestSubTitle|Croatia| British English  | Package |
#      |TestFeaturePageLuanda|LuandaFeaturePage|TestSubTitle|Angola - Luanda|Portuguese|Feature|
#      |TestHubPageLuanda|LuandaHubPage|TestSubTitle|Angola - Luanda|Portuguese|Hub|
#      |TestFeaturePackageLuanda|LuandaFeaturePackage|TestSubTitle|Angola - Luanda| Portuguese  | Package |
#      |TestHubPageHongKong|HongKongHubPage |TestSubTitle|Hong Kong| British English  | Hub |
#      |TestFeaturePageHongKong|HongKongFeaturePage |TestSubTitle|Hong Kong| British English  | Feature |
#      |TestFeaturePackageHongKong|HongKongFeaturePackage |TestSubTitle|Hong Kong| British English  | Package |

      |TestFeaturePageTokyo-English|TokyoFeaturePage-English |TestSubTitle|Japan - Tokyo | American English | Feature |
      |TestHubPageTokyo-English|TokyoHubPage-English |TestSubTitle|Japan - Tokyo | American English | Hub |
      |TestFeaturePackageTokyo-English|TokyoFeaturePackage-English |TestSubTitle|Japan - Tokyo | American English | Package |
      |TestFeaturePageTokyo-JP|TokyoFeaturePage-JP |TestSubTitle|Japan - Tokyo | Japanese | Feature |
      |TestHubPageTokyo-JP|TokyoHubPage-JP |TestSubTitle|Japan - Tokyo | Japanese | Hub |
      |TestFeaturePackageTokyo-JP|TokyoFeaturePackage-JP |TestSubTitle|Japan - Tokyo | Japanese | Package |

#


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
#    |LondonFeaturePage|UK - London|Modified Test Sub Title|Complete |
#    |LondonFeaturePage|UK - London|Deleting This Page|Deleted |
#    |AtlantaFeaturePage|US - Atlanta|Modified Test Sub Title|Complete |
#    |AtlantaFeaturePage|US - Atlanta|Deleting This Page|Deleted |
#    |SeoulFeaturePage|South Korea - Seoul|Modified Test Sub Title|Complete |
#    |SeoulFeaturePage|South Korea - Seoul|Deleting This Page|Deleted  |
#    |TokyoFeaturePage|Japan - Tokyo|Modified Test Sub Title|Complete |
#    |TokyoFeaturePage|Japan - Tokyo|Deleting This Page|Deleted |
#    |NashvilleFeaturePage|US - Nashville|Modified Test Sub Title|Complete |
#    |NashvilleFeaturePage|US - Nashville|Deleting This Page|Deleted |
#    |CroatiaFeaturePage|Croatia|Modified Test Sub Title|Complete |
#    |CroatiaFeaturePage|Croatia|Deleting This Page|Deleted |
#    |LuandaFeaturePage|Angola - Luanda|Modified Test Sub Title|Complete |
#    |LuandaFeaturePage|Angola - Luanda|Deleting This Page|Deleted |
#    |HongKongFeaturePage|Hong Kong|Modified Test Sub Title|Complete |
#    |HongKongFeaturePage|Hong Kong|Modified Test Sub Title|Deleted |
    |TokyoFeaturePage-English|Japan - Tokyo|Modified Test Sub Title|Complete |
    |TokyoFeaturePage-English|Japan - Tokyo|Deleting This Page|Deleted |
    |TokyoFeaturePage-JP|Japan - Tokyo|Modified Test Sub Title|Complete |
    |TokyoFeaturePage-JP|Japan - Tokyo|Deleting This Page|Deleted |

#    @addEditDeleteZone
#    Scenario: I can able to add a New Zone to the existing Page
#      Given I am on the Pages Page
#      When I search for the Page with Keyword 'Test Feature',Site as 'UK - London' and Status as 'Complete'
#      And I select the recently created Page with the name 'Test Feature'
#      Then I should be navigate to the 'Edit page' Page