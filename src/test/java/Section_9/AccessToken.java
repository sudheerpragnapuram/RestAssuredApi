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

public class AccessToken {

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
		
		System.out.println("New reponse body is"+response);


		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");

		System.out.println("New Access token is  " +accessToken);

		String response1  = given().given().contentType("application/json").queryParam("access_token", accessToken)
				.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response1);


	}
}