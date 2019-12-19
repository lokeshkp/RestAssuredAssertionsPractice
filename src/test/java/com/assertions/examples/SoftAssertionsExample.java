package com.assertions.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SoftAssertionsExample {

	private static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";

	@BeforeClass
	public static void init(){
		RestAssured.baseURI 	= "http://api.walmartlabs.com";
		RestAssured.basePath 	= "/v1";
	}

	@Test
	public void test01(){
		given().queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").
		when().get("/search").
		then().
			body("items[0].imageEntities[0]", hasKey("thumbnailImage"),
					"items[0].salePrice", equalTo("53.88"),
					"items[0].numReviews", equalTo(2));
	} 
	
	@Test
	public void test02(){
		float res = given().queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").
		when().get("/search").
		then().extract().path("items[0].salePrice");
	
		System.out.println(res);
	} 
}
