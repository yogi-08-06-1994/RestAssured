package com.qa.restapi.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.restapi.base.Testbase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class rest_api_singleempGET extends Testbase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
	
	logger.info("*********Started TC001_Get_All_Employees **********");
		
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	response = httpRequest.request(Method.GET,"/employee/"+empID);
	
	Thread.sleep(5);
	}
			

		
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime = response.getTime(); // Getting status Line
		Assert.assertTrue(responseTime<6000);
		
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx");
	}

	@Test
	void checkContentLenght()
	{
		String contentLength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
	}
	
	
}
