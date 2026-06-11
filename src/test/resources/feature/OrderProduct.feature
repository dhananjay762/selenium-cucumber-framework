Feature: Order Products

  @Regression @aut_Dhananjay
  Scenario Outline: Verify E2E Workflow of Ordering Products
    Given User is on Login page
		When I enter username as "<UserName>"
		And  I enter password as "<Password>"
		And  I click on Login button
		Then I should be navigated to the Inventory page
		When I select the product "<ProductName>" and add to cart
		And I click on the cart icon
		Then I should be able to verify the added product
		When I click on checkout button
		And I enter the firstname, lastname and zipcode as "<FirstName>", "<LastName>" and "<ZipCode>" respectively
		And I click on continue button
		Then Verify the checkout product details
		When I click on finish button
		Then Order should be successfully placed
		When I click on logout button
		Then I should be able to logged out from the application

    Examples: 
      | UserName    | Password    | ProductName  						| FirstName | LastName  |  ZipCode  |
      |standard_user| secret_sauce| Sauce Labs Backpack		  |   John    |   Doe     |  64901    |
      |standard_user| secret_sauce| Sauce Labs Bolt T-Shirt |   Will    |   Smith   |  76109    |
