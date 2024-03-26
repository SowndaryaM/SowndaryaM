###########################################################################################################
#This feature will do configuration setup to start the regression suite execution
#Feature created on : 07-06-2023
#APITesting.java
#LoginDetails.properties
###########################################################################################################
	
Feature: To test API
	
Scenario: To test the features using API
Given Create a user
Then Generate authentication token by using the credentials in ContextStore
And Filter books and assign to user in context
