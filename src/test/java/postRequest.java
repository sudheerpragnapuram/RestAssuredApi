import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postRequest {

	@Test
	public void registrationPost(){

		//specify base URI
		RestAssured.baseURI="https://reqres.in/";

		//Request object
		RequestSpecification httprequest = RestAssured.given();



		//Request Body with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "sudheer");
		requestParams.put("job", "Testerdone");

		httprequest.header("Content-Type", "application/json");	

		httprequest.body(requestParams.toJSONString());

		//Response object
		Response response = httprequest.request(Method.POST,"/api/users");


		//Response
		String responsebody = response.getBody().asString();
		System.out.println("ResponseBody is "+responsebody);

		//Status code
		int statusCode = response.getStatusCode();
		System.out.println("Satus code is "+ statusCode);
		Assert.assertEquals(statusCode, 201);

		//Status line verification
//		String statusLine = response.getStatusLine();
//		Assert.assertEquals(statusLine, "");
		

	}

}
