@addVenue
Feature: Add,Edit and delete a Venue in CMS
#As a an Admin
#I want to see Venue Page
#so that I can add edit and delete a Venue

  Background:
    Given I am Logged-In

  Scenario Outline: I can able to add a new venue
    When I add a Venue, I supply the information '<Site>','<Language>','<Venue>','<City>'
    And I save it
    Then the Venue is created and should see message as 'The venue was created successfully.'
    And I should be navigate to the 'Edit venue' Page
    When I add taxonomy for Venue
    And I go back to Edit Venue Page
    And I change the Venue status as 'Complete'
    And I save it
    Then I should see the message as 'The venue was saved successfully.'
#    And I logout
#    Then I should redirect to Login Page
Examples:
  |Site |Language|Venue|City|
#  |UK - London |British English|AutoTestVenueLondon|London|
#  |South Korea - Seoul|American English|AutoTestVenueSeoul|Seoul|
  |Japan - Tokyo|American English|EnglishVenueTokyo|Tokyo|
  |Japan - Tokyo|Japanese|JapaneseVenueTokyo|Tokyo|
#  |US - Nashville |American English|AutoTestVenueNashville|Nashville|
#  |Croatia |British English|AutoTestVenueCroatia|Croatia|
#  |Angola - Luanda|Portuguese|AutoTestVenueLuanda|Luanda|
#  |Hong Kong |British English|AutoTestVenueHongKong|Hong Kong|

  @editanddeleteVenue
  Scenario Outline: I can able to find the newly added venue in the list and I can Edit and Delete
    Given I am on the Venues Page
    When I search venue with the Name '<Venue>',Site '<Site>',Status 'Complete' and UpdatedInLast 'Week'
    And I select the recently created Venue with the name '<Venue>'
    Then I should be navigate to the 'Edit venue' Page
    When I changes the BuildingNo as 'Auto-Test-Building_01' and Author as 'Srikanth' and Status as '<Status>'
    And I change the Posted Date as currentdate
    And I save the Venue
    Then I should see the message as 'The venue was saved successfully.'
  Examples:
  |Venue|Site|Status|
#  |AutoTestVenueLondon|UK - London|Complete|
#  |AutoTestVenueLondon|UK - London|Deleted |
#  |AutoTestVenueSeoul|South Korea - Seoul |Complete|
#  |AutoTestVenueSeoul|South Korea - Seoul |Deleted|
#  |AutoTestVenueTokyo|Japan - Tokyo |Complete|
#  |AutoTestVenueTokyo|Japan - Tokyo |Deleted|
#  |AutoTestVenueCroatia|Croatia|Complete|
#  |AutoTestVenueCroatia|Croatia|Deleted |
#  |AutoTestVenueNashville|US - Nashville|Complete|
#  |AutoTestVenueNashville|US - Nashville|Deleted |
#  |AutoTestVenueLuanda|Angola - Luanda|Complete|
#  |AutoTestVenueLuanda|Angola - Luanda|Deleted|
#  |AutoTestVenueHongKong|Hong Kong|Complete|
#  |AutoTestVenueHongKong|Hong Kong|Deleted|
  |EnglishVenueTokyo|Japan - Tokyo |Complete|
  |EnglishVenueTokyo|Japan - Tokyo |Deleted|
  |JapaneseVenueTokyo|Japan - Tokyo |Complete|
  |JapaneseVenueTokyo|Japan - Tokyo |Deleted|
