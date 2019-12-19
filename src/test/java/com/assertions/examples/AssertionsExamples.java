package com.assertions.examples;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class AssertionsExamples {

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
		then().body("items[0].imageEntities[0]", hasKey("thumbnailImage"));
			
	}
	

	@Test
	public void test02(){
		given().queryParam("query", "iphone").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").
		when().get("/search").
		then().body("items.findAll{it.name='iPhone XS Max 256GB Gold (Unlocked) Refurbished A+'}", hasItems(hasEntry("name","iPhone XS Max 256GB Gold (Unlocked) Refurbished A+")));
			
	}
}
