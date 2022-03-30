package Rahul_API;

import static org.hamcrest.Matchers.equalTo;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload_child;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class DynamicJson {
	
	@Test(dataProvider="BookData")
	public void addBook(String isbn, String aisle){
		
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().log().all().header("Content-Type","application/json").body(payload_child.Addbook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("*****Checking Adding Book**** is"+ response);
		
		//JsonPath js = ReUsableMethod.rawToJson(response);
		// OR
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		
		System.out.println("New id is " +id);
		
		//DeleteBook
		
		
//		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
//				.queryParam("place_id  ", placeId)
//				.when().get("maps/api/place/get/json")
//				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		String message = given().log().all().header("Content-Type","application/json").body("{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				" \r\n" + 
				"} \r\n" + 
				"")
		.when().delete("/Library/DeleteBook.php")
		.then().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted")).extract().response().asString();
		
		System.out.println("*****Checking Message**** is"+ message);


		
		
	
	}
	
	@DataProvider(name="BookData")
	public Object[][] getData(){
		
		//array = collection of elements
		//multidimensional arrays = collection of arrays
		return new Object[][]{{"chakis","64547"},{"kouer","8262"},{"Gresjde","37383"}};
		
	}

}
