###########################################################################################################
#This feature will do configuration setup to start the regression suite execution
#Feature created on : 07-06-2023
#APITesting.java
#LoginDetails.properties
###########################################################################################################
	
Feature: To test demoqa frontend
	
Scenario: To Verify all the details from demoqa website
Given Login to the demoQA website
Then Go to the book store application
And Login to the application
Then Verify the number of books that were added to user against the number of books displayed in the website
And For each book on the website, verify the book details
