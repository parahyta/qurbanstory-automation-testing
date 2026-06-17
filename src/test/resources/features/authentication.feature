@smoke
@authentication
Feature: Authentication

  Scenario: TC-LGN-01 User Login Menggunakan Google OAuth
    Given user is on the login page
    When user clicks "Login with Google"
    Then user should be redirected to the customer dashboard
