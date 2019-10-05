Feature: Smoke testing of adding cart

Scenario: Adding cart
Given I visit shopping Website
When I click on sign in and login
Then  I click on the Dresses tab and do shopping
When I click proceed to checkout
Then I will complete payment
Then Verify the complete succesful order