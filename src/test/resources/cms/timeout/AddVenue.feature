Feature: Adding a Venue from CMS
#As a an Editor
#I want to see add venue option
#so that I can add a new venue
#@login
@Venue
Scenario: Editor can able to login successfully  with valid credentials
Given Editor is on Login Page
When Editor Enters Username and Password and selects Login
Then Editor should be logged successfully
When Editor logout
Then editor should navigate to LoginPage


@Venue
Scenario: Editor can able to add a new venue
Given Editor is logged-In
And Editor is on HomePage
Then Editor can see Venues Option
When Editor selects Venues Option
Then Editor should navigate to Venues Page
When Editor selects Add Venue Option
Then Editor should be navigate to Add Venue Page
When Editor selects Language as 'Spanish'
And Editor enters Name as 'Sri' and City as 'London'
And Editor selects Site as 'Spain - Madrid'
And selects Save Option
Then Editor should see message as 'The venue was created successfully.'
And Editor should be navigate to 'Edit venue' Page

#@verifyvenue
@Venue
Scenario: Editor can able to check the newly added venue in the venue list
Given Editor is in Venues Page
When Editor selects Name as 'Sri' and Site as 'Spain - Madrid'
And selects filter
Then Editor should see the list of venues with the name 'Sri' and Site 'Spain - Madrid'
