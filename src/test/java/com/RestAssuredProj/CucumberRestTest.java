package com.RestAssuredProj;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

public class CucumberRestTest {
	
	@Test
	public void testGet(){
		
		//RestAssured.baseURI ="http://ergast.com/api/f1/drivers.json";
		
		given().
		when().
			get("http://ergast.com/api/f1/drivers.json").
		then().statusCode(200);

	}

}
