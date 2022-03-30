package Section_11_12;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import groovyjarjarantlr4.v4.parse.ANTLRParser.id_return;

public class serializeTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		addplace ap = new addplace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");

		// creating object for Types
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		ap.setTypes(mylist);

		//Creating object for Location Class to call into this class
		Location loction = new Location();	
		loction.setLat(-38.383494);
		loction.setLng(33.427362);
		ap.setLocation(loction);

		//***********POST****************
		//Complete URL : https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
		String response = given().log().all().queryParam("key", "qaclick123").body(ap).header("Content-Type","application/json")
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).contentType("application/json").extract().response().asString();		
		System.out.println(response);

		//**********Get*********
		//Complete URL : http://rahulshettyacademy.com/maps/api/place/get/json?place_id=xxxx&key=qaclick123

		JsonPath js = new JsonPath(response);
		String id= js.getString("place_id");
		String getmessage = given().log().all().queryParam("place_id", id).queryParam("key", "qaclick123").header("Content-Type","application/json")
				.when().log().all().get("/maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).contentType("application/json").extract().response().asString();
		System.out.println("****Get Message is*******" +getmessage);

		//***********PUT***********

		//		Complete URL : http://rahulshettyacademy.com/maps/api/place/get/json?place_id=xxxx&key=qaclick123

		String putMessage = given().log().all().header("Content-Type","application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+id+"\",\r\n"
						+ "\"address\":\"152727 Fullford Road, New Zealand\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when().put("/maps/api/place/update/json")
				.then().assertThat().statusCode(200).contentType("application/json").extract().response().asString();

		System.out.println("******Update Message is "+putMessage);




		//************DELETE********
		//Complete URL: https://rahulshettyacademy.com/maps/api/place/delete/json?key=qaclick123

		//		JsonPath js = new JsonPath(response);
		//		String id= js.getString("place_id");
		System.out.println("***Place ID for Delete is******** "+id);

		String message = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "    \"place_id\":\""+id+"\"\r\n"
				+ "}\r\n"
				+ "")
				.when().delete("/maps/api/place/delete/json")
				.then().log().all().assertThat().statusCode(200).contentType("application/json").body("status", equalTo("OK")).extract().response().asString();

		System.out.println("Success message is "+message);

	}

}
