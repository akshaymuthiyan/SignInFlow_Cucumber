Feature: SignIn

Scenario: Successful Login with Valid Credentials
  Given User Launch Chrome browser
  When User opens URL "https://magento.softwaretestingboard.com/"
  And User clicks on Sign In link
  And User enters Email as "admin012@gmail.com" and Password as "Test@12344"
  And Click on SignIn
  Then Page Title should be "Home Page"
  When User click on Sign out link
  Then User should see logout confirmation message
  And Close the browser