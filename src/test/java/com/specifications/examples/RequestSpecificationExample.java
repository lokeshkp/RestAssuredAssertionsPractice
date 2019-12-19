package com.specifications.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationExample {

	private static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";
	static RequestSpecBuilder reqBuilder;
	static RequestSpecification reqSpec;

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

	}

	@Test
	public void test01(){
		given().spec(reqSpec).
		when().get("/search").
		then().log().all();
	} 
}
