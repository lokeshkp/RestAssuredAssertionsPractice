package com.responseextraction.rootpath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class RootPathExamples1 {

	private static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";

	@BeforeClass
	public static void init(){
		RestAssured.baseURI 	= "http://api.walmartlabs.com";
		RestAssured.basePath 	= "/v1";
		RestAssured.rootPath	= "items[0]";
	}

	@Test
	public void test01(){
		given().queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").
		when().get("/search").
		then().
			root("items[0]").
			body("salePrice", equalTo("53.88"),
					"numReviews", equalTo(2));
	} 
	
	@Test
	public void test02(){
		given().queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").
		when().get("/search").
		then().
			body("salePrice", equalTo("53.88"),
					"numReviews", equalTo(2));
	} 
	
	@AfterClass
	public void cleanUp(){
		RestAssured.reset();
	}
}
