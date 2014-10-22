@addPost
Feature: Add,Edit and delete a Post
#As a an Admin
#I want to see Posts Page
#so that I can add edit and delete a Post

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new Post
  Given I am on the Posts Page
  Then I selects blog name 'Chicago Blog (Chicago - En)'
  When I add a Post
  And I supply postInformation
  |Test Title     |Category|Blog                       |Body Text|
  |Test Post Title|Movies    |Chicago Blog (Chicago - En)|Test Body Text|
  And I save the Post and Publish
  Then the Post is created and should see message as 'Your post was updated successfully.'
  When I go to 'Posts' Page
  Then I Should see recently added Post 'Test Post Title'

  @editanDeletePost
  Scenario: I can able to find the newly added Post in the Post list and I can Edit
    Given I am on the Posts Page
    Then I selects blog name 'Chicago Blog (Chicago - En)'
    When I selects recently added post 'Test Post Title'
    Then I should be navigate to the 'Edit post' Page
    When I changes post title as 'Modified Post Title' and Body Text as 'Modified Body Text'
    And I Update the Post
    Then I should see the message as 'Your post was updated successfully.'

  @editanDeletePost
  Scenario: I can able to find the newly added Post in the Post list and I can Delete
    Given I am on the Posts Page
    Then I selects blog name 'Chicago Blog (Chicago - En)'
    When I selects recently added post 'Test Post Title'
    Then I should be navigate to the 'Edit post' Page
    And I Delete the Post
    Then I should see the message as 'Your post was deleted successfully.'
