Feature: Login Functionality Verification

	@Smoke @aut_Dhananjay
	Scenario: Verify Login Functionality for Valid User
		Given User is on Login page
		When I enter username as "standard_user"
		And  I enter password as "secret_sauce"
		And  I click on Login button
		Then I should be navigated to the Inventory page
	
	@Smoke @NegativeTest @aut_Dhananjay
	Scenario: Verify Login Functionality for Invalid User
		Given User is on Login page
		When I enter username as "invalid_user"
		And  I enter password as "secret_sauce"
		And  I click on Login button
		Then Application should throw validation message for incorrect credentials