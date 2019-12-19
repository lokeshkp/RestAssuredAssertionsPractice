package com.specifications.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationExample {

	private static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";
	static RequestSpecBuilder reqBuilder;
	static RequestSpecification reqSpec;
	
	static ResponseSpecBuilder resBuilder;
	static ResponseSpecification resSpec;
	
	

	@BeforeClass
	public static void init(){
		RestAssured.baseURI 	= "http://api.walmartlabs.com";
		RestAssured.basePath 	= "/v1";
		
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addQueryParam("query", "ipod");
		reqBuilder.addQueryParam("apiKey", APIKEY);
		reqBuilder.addQueryParam("format", "json");
		reqBuilder.addQueryParam("facet", "on");
		reqBuilder.addHeader("Accept", "*/*");
		
		reqSpec = reqBuilder.build();
		
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		resBuilder.expectHeader("Server", "Mashery Proxy");
		resBuilder.expectStatusCode(200);
		resBuilder.expectBody("query",equalTo("ipod"));
		resBuilder.expectBody("numItems",equalTo(10));
		resBuilder.expectBody("items.name",hasItem("Apple iPod nano 16GB (Space Gray)"));
		
		resSpec = resBuilder.build();
	}

	@Test
	public void test01(){
		given().spec(reqSpec).
		when().get("/search").
		then().spec(resSpec);
	} 
	
	
}
