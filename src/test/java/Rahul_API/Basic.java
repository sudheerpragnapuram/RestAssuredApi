package Rahul_API;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload_child;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;


public class Basic {

	@Test
	public void basic(){
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload_child.Addplace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

		System.out.println(response);

		JsonPath js = new JsonPath(response);

		String placeId = js.getString("place_id");

		System.out.println(placeId);

		//Update place

		//		String newaddress = "Summer Walk Africa";
		//		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		//		.body("{\r\n" + 
		//				"\"place_id\":\""+placeId+"\",\r\n" + 
		//				"\"address\":\""+newaddress+"\",\r\n" + 
		//				"\"key\":\"qaclick123\"\r\n" + 
		//				"}\r\n" + 
		//				"}")
		//		.when().put("maps/api/place/update/json")
		//		.then().assertThat().log().all().statusCode(200);

		String newAddress = "Summer Walk, Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));



		//Get place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id  ", placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();

		//JsonPath jsplace = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReUsableMethod.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);


	}
}
