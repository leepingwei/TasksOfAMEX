Feature: Shopping

  Scenario: Shopping the cheapest item
    Given Navigate to saucedemo website
    When Standard user enter valid "standard_user" and "secret_sauce"
    Then User click Login button to login
    Then Verify user is on the homepage
    Then Find out the cheapest item add to the cart and verify
    Then User click continue shopping
    Then Go to cart and click checkout
    Then Fill up with "Lee" "test" "11354" and click continue
    Then Verify the item total price and click finish
    Then Shopping process complete and user suppose to see a Thank you message
    Then User quit from the browser