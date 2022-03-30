package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class DDT_Post {

	@Test(dataProvider="empdataprovider")
	public void postNewEmployee(String ename, String esal, String eage){
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

		//*******Post Request with Sigle user********

		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestparams = new JSONObject();
		requestparams.put("name",ename);
		requestparams.put("salary",esal);
		requestparams.put("age",eage);


		httpRequest.header("Content-Type", "application/json");	

		httpRequest.body(requestparams.toJSONString());

		//post request

		Response response = httpRequest.request(Method.POST,"/create");

		//Response
		String responsebody = response.getBody().asString();
		System.out.println("ResponseBody is "+responsebody);

		Assert.assertEquals(responsebody.contains(ename), true);
		Assert.assertEquals(responsebody.contains(esal), true);
		Assert.assertEquals(responsebody.contains(eage), true);		

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);




	}



	//****** MultiUser******Data provider Method**********
	@DataProvider(name="empdataprovider")
	String[][] getEmpData() throws IOException
	{

		String path = System.getProperty("user.dir")+"/src/test/java/datadriventesting/Excel.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int coloumn = XLUtils.getCellCount(path, "Sheet1", 1);

		String empdata[][]= new String[rownum][coloumn];
		for(int i =1; i<=rownum; i++)
		{
			for(int j=0; j<=coloumn;j++){
				empdata[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		//		String empdata[][]={{"sure","3452","24"},{"okay","12452","48"},
		//				{"yeah","75452","36"}};
		return(empdata);
	}


}
