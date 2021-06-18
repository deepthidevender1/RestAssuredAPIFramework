package Demo;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.http.ContentType;
import utils.ExcelUtils;


public class RestassuredAPIPOSTDemo {
	
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	String TestAPiUrl;
	String TestResource;
	String TestResourceId;
	
	@BeforeSuite
	@Parameters({"TestAPiUrl", "TestResource", "TestResourceId"})
	public  void Setup(String MyTestAPiUrl, String MyTestResource, String MyTestResourceId)
	{
		
		//Initialise the "TestAPiUrl", "TestResource", "TestResourceId"
		TestAPiUrl = MyTestAPiUrl;
		TestResource = MyTestResource;
		TestResourceId = MyTestResourceId;
	
	}
	
	 //gets the data from excel
	  //
	  @BeforeTest
	  @DataProvider(name = "testdata") 
	  public Object[][] getdata() throws IOException 
	  { 
		  String Projpath = System.getProperty("user.dir"); 
		  Object data[][] = testdata(Projpath + "\\Excel\\Data.xlsx", "Sheet1"); 
		  return data;
	  }
	  
	  //Function to read the data from excel file
	  public Object[][] testdata(String excelPath, String SheetName) throws IOException
	  
	  { 
		  ExcelUtils excelutil = null;
		  excelutil = new ExcelUtils(excelPath , SheetName);
		  int rowCount =  excelutil.getRowCount(); 
		  int colCount = excelutil.getColCount(); 
		  
		  Object data[][] = new Object[rowCount-1] [colCount]; 
		  for (int i = 1; i < rowCount; i++) 
		  { 
			  for (int j = 0; j < colCount; j++) 
			  { 
				  data[i-1][j] =  excelutil.getcelldataValue(i, j); 
				 
								  
			}
	  
	  }
	  
		  return data;
	  
	  }
	 

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "testdata")
	void test_1(String TestFirstname, String Testlastname,  String TestId ) throws Exception{
		
	
        
        JSONObject request = new JSONObject();
		request.put("Firstname",TestFirstname);
		request.put("Lastname", Testlastname);
		request.put("id", TestId);
		
		baseURI = TestAPiUrl;
		
		given().
		contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.header("Content-Type", "application/json")
		.body(request.toJSONString())
		.when()
		.post(TestResource)
		//.post("/users")
		.then()
		.statusCode(201)
		.log().all();
		
	}
	
		
	@AfterTest
	public static void TearDownTest()
	{
		System.out.println("Tests completed Successfully");
	}
	
	@AfterSuite
	public void TearDown()
	{

	
		System.out.println("Suite completed Successfully");
	}
	
}
