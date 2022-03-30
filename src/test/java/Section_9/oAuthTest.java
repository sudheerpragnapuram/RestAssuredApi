package Section_9;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Section_11_12.Api;
import Section_11_12.GetCourse;
import Section_11_12.Mobile;
import Section_11_12.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//To hit Browser
		//		String projectPath = System.getProperty("user.dir");
		//		System.out.println("Project path is " +projectPath);
		//
		//		System.setProperty("webdriver.chrome.driver", projectPath+"/resources/drivers/chromedriver.exe");
		//
		//		WebDriver driver = new ChromeDriver();
		//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_url=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		//
		//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("sjsiuej");
		//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		//		Thread.sleep(3000);
		//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("jwiksks");
		//		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		//		Thread.sleep(3000);
		//		String url=driver.getCurrentUrl();
		//		String partialcode = url.split("code")[1];
		//		String code = partialcode.split("&scope")[0];
		//		System.out.println(code);

		//String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";

		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhTnOVQkc6FyyT-GuvdmYsz8Q72F3PO8OaRZdLVUyo859Fc6UtrNkgItbPHYj8j-Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		
		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];

		System.out.println("New code from Get request is  " +code);

		//To get Access code
		String response =  given().log().all()
				.urlEncodingEnabled(false)
				.queryParams("code",code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code")
				.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(url);
		
//		https://www.googleapis.com/oauth2/v4/token?
//			code=4%2F0AX4XfWirJQ894t7sSqUam9iPwcMdNOkykl-8gzO3mEtZdgWMPmB60ZBeoqgtGHxE3iUlvA&
//			client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&
//			client_secret=erZOWM9g3UtwNRj340YYaK_W&
//			redirect_uri=https://rahulshettyacademy.com/getCourse.php&
//				grant_type=authorization_code&
//				scope=https://www.googleapis.com/auth/userinfo.email&response_type=code&authuser=1&prompt=consent#

		//				.queryParams("state", "verifyfjdss")
		//
		//				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

		// .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")







		//		JsonPath jsonPath = new JsonPath(response);
		//
		//		String accessToken = jsonPath.getString("access_token");
		//
		//		System.out.println(accessToken);
		//
		//		String r2=    given().contentType("application/json").
		//
		//				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
		//
		//				.when()
		//
		//				.get("https://rahulshettyacademy.com/getCourse.php")
		//
		//				.asString();
		//
		//		System.out.println(r2);



		//Old code

		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token1");


		System.out.println("New Access token is  " +accessToken);



//		String response1 = given().given().contentType("application/json").queryParam("access_token", "ya29.A0ARrdaM9hdIBqAOp-2uMERuiSr4P2mKKU74u8Xm8d0_iR_Ysdw8EBVrM3rtbkN_3BuKnWA7k-GZn241T0Clli2EBmx9LoAbEsOaTNq2coG8b5gAF7oYcRz-9IzqIAsh3p2QoIhbOi3giBrUmjlFVaZiLnDb8i2A").expect().defaultParser(Parser.JSON)
//				.when().get("https://rahulshettyacademy.com/getCourse.php").asString();

		
		// For PoJoClass
		GetCourse gc = given().given().contentType("application/json").queryParam("access_token",accessToken )
				.expect().defaultParser(Parser.JSON)
				.expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getServices());
		System.out.println(gc.getUrl());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		System.out.println(gc.getCourses().getWebAutomation().get(2).getCourseTitle());
		
		List<Api> apicourses = gc.getCourses().getApi();
		
		for(int i=0; i<apicourses.size(); i++) {
			
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("price is  "+apicourses.get(i).getPrice());
				break;				
			}
			
		}
		
		List<Mobile> mobile= gc.getCourses().getMobile();
		for(int i=0; i<mobile.size(); i++) {
			
			if(mobile.get(i).getPrice().equalsIgnoreCase("50")) {
			System.out.println("Mobile CourseTitle is	"+mobile.get(i).getCourseTitle());
			break;
			}
		}
		
		// Get all the courseTitle names of WebApplication 
		ArrayList<String> a = new ArrayList<String>();
		
		List<Section_11_12.WebAutomation> webautomation =gc.getCourses().getWebAutomation();
		
		for(int j =0; j<webautomation.size();j++) {
			
			System.out.println("All CourseTitle name are "+webautomation.get(j).getCourseTitle());
			
			a.add(webautomation.get(j).getCourseTitle());
			//if (webautomation.get(j).getCourseTitle()) {
		}
		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
	
		

		
		//System.out.println(response1);

	}

}
