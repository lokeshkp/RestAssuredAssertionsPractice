package com.qa.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restapi.RestClient;

public class GetAPITest extends TestBase{
		
	public GetAPITest() throws FileNotFoundException {
		super();
	}

	TestBase testbase;	
	RestClient rsClient;
	String resource;
	String url;
	String apiURL;
	
	@BeforeMethod
	public void setUP() throws ClientProtocolException, IOException, JSONException{
		testbase = new TestBase();
		resource = prop.getProperty("endUrl");
		apiURL = prop.getProperty("resource");
		url = resource+apiURL;
		System.err.println(url);
	}
	
	@Test
	public void getTest() throws ClientProtocolException, IOException, JSONException{
		rsClient = new RestClient();
		rsClient.get(url);
	}
	
	@AfterMethod
	public void exit(){
		System.out.println("Completed....");
	}

}
