package Rahul_API;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload_child;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	@Test
	public void Testing() {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload_child.CoursePrice());

		//		1. Print No of courses returned by API

		int count = js.getInt("courses.size");
		System.out.println(count);

		//		2.Print Purchase Amount
		

		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		//
		//		3. Print Title of the first course
		String title =js.get("courses[1].title");
		System.out.println(title);
		//
		//		4. Print All course titles and their respective Prices

		for(int i=0;i<count; i++){
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());

			System.out.println(courseTitles);

		}
		//
		//		5. Print no of copies sold by RPA Course

		System.out.println("Print no of copies sold by RPA Course");

		for(int i = 0; i<count; i ++){
			String courseTille = js.get("courses["+i+"].title");

			if(courseTille.equalsIgnoreCase("RPA")){

				int cpoies = js.get("courses["+i+"].copies");
				System.out.println(cpoies);
				break;

			}

		}

		
		//		6. Verify if Sum of all Course prices matches with Purchase Amount
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
	}
	
	@Test
	public void sumOfCourses(){
		
		System.out.println("******Second class**********");
		
		int sum = 0;
		JsonPath js = new JsonPath(payload_child.CoursePrice());
		
		int count = js.getInt("courses.size");
		
		for (int i = 0; i<count; i ++){
			
			int prices = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			
			int amount = prices*copies;
			System.out.println(amount);
			
			 sum = sum+amount;
			
			
		}
		System.out.println(sum);
		
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(sum, purchaseamount);
		
	}

}
