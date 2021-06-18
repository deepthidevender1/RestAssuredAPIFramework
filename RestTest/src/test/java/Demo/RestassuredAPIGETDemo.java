package Demo;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utils.ExcelUtils;



public class RestassuredAPIGETDemo {
	
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	
	
	@BeforeSuite
	public  void Setup()
	{
		// initialize the HtmlReporter
		htmlReporter = new ExtentHtmlReporter("extent.html");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();

		// attach only HtmlReporter
		extent.attachReporter(htmlReporter);
		//Setup driver
	
	}
	
	
	@Test
	@Parameters({"TestAPiUrl", "TestResource", "TestResourceId"})
	void test_1(String TestAPiUrl, String TestResource, @Optional String TestResourceId ) throws Exception{
		
		// creating tests
		ExtentTest test = extent.createTest("Rest API Test", "This is Get request");
		 // log(Status, details)
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        
        baseURI = TestAPiUrl;
		
        given().get(TestResource + TestResourceId).then().statusCode(200).log().all();
		
        // info(details)
        test.info("This step shows usage of info(details)");
        
        // log with snapshot
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        
        // test with snapshot
        test.addScreenCaptureFromPath("screenshot.png");
        
        // calling flush writes everything to the log file
        extent.flush();
    }
	
	
	
	
	
	@AfterTest
	public static void TearDownTest()
	{
		System.out.println("Tests completed Successfully");
	}
	
	@AfterSuite
	public void TearDown()
	{
		// calling flush writes everything to the log file
        extent.flush();	
	}
	
}
