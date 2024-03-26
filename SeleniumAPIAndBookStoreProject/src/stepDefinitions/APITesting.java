package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITesting {
	
	
	public static final String URL = "https://bookstore.toolsqa.com";
	public static final String USERNAME = "yghu";
	public static final String PASSWORD = "abghU$123";
	public static String USER_ID = null;
	

	public static String token;
	public static Response response;
	public static String jsonString;
	public static String bookId;
	 List<String> set = new ArrayList<String>();
	public static int noOfBooks=0;
	
	
	
	@Given("^Create a user$")
	public void create_a_user() throws Throwable {
	   
		RestAssured.baseURI = URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
				.post("/Account/v1/User");

		jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");
		
		System.out.println(jsonString + " : " + token );
		
		String split1[] = jsonString.split(":");
		String unitRateValue_output1 = split1[1].trim();
		System.out.println(unitRateValue_output1);
		String split2[] = unitRateValue_output1.split(",");
		String unitRateValue_output2 = split2[0].trim();
		System.out.println(unitRateValue_output2);
		USER_ID = unitRateValue_output2.replaceAll("\"", "");
		System.out.println(USER_ID);
		//System.out.print("API response body = " + response.getBody().asString());
		
		
		
	}

	@Then("^Generate authentication token by using the credentials in ContextStore$")
	public void generate_authentication_token_by_using_the_credentials_in_ContextStore() throws Throwable {
	  
		RestAssured.baseURI = URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
				.post("/Account/v1/GenerateToken");

		jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");
		
		System.out.println(jsonString + "" + token + "####################################");
	}

	@Then("^Filter books and assign to user in context$")
	public void filter_books_and_assign_to_user_in_context() throws Throwable {
	    
		RestAssured.baseURI = URL;
		RequestSpecification request = RestAssured.given();
		response = request.get("/BookStore/v1/Books");

		jsonString = response.asString();
		List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
		for(int index = 0 ; index < books.size() ; index++){
		    Map<String, String> listItem = books.get(index);
		    System.out.println(listItem.keySet()+""+ listItem.values());
		    
		   if (listItem.containsKey("publisher") && listItem.containsValue("O'Reilly Media"))
		   {
		   System.out.println("@@@@@@@@@"+books.get(index).get("isbn"));
		   bookId = books.get(index).get("isbn");
		   System.out.println(bookId+"%%%%%%ssssssssssssssssssssssssssssssssssssssssssssssssssssssssss%%%%%%");
		
		   set.add(bookId);
		  System.out.println(set.size()+"*********************************************************************************");
		   RestAssured.baseURI = URL;
			RequestSpecification request2 = RestAssured.given();
			request2.header("Authorization", "Bearer " + token)
			.header("Content-Type", "application/json");

			response = request2.body("{ \"userId\": \"" + USER_ID + "\", " +
					"\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
					.post("/BookStore/v1/Books");
		   }
		}
		noOfBooks = set.size();
		System.out.println(noOfBooks);
	}

	
	
}