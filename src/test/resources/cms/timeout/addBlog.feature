@addBlog
Feature: Add a new Blog in CMS
#As a an Admin
#I want to see Blogs Page
#so that I can add a Blog

  Background:
    Given I am Logged-In

  Scenario: I can able to add a new Blog
    Given I am on the Blogs Page
    When I add a Blog
    And I supply the Blog information
      | Blog Name    | Url Slug       | Site       | Language         |
      | Test BlogName| /london/blogs/ |UK - London | British English  |
    And I save blog
    Then the Blog is created and should see message as 'Your blog was saved successfully.'
    And I should be navigate to the 'Blog list' Page
    And I should see recently added blog 'Test BlogName' in the blog list