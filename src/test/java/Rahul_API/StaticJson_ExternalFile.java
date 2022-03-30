package Rahul_API;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import files.payload_child;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticJson_ExternalFile {
	@Test
	public static void main() throws IOException{
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\sudheer.pragnapuram\\OneDrive - Datacom\\Documents\\API-RahulShetty\\addplace.json")))).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	
//	JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
//	String actualAddress =js1.getString("address");
//	System.out.println(actualAddress);
//	Assert.assertEquals(actualAddress, "Pacific ocean");
	
	JsonPath jsplace = new JsonPath(getPlaceResponse);
	String actualAddress = jsplace.getString("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress, newAddress);
	//Cucumber Junit, Testng
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}
