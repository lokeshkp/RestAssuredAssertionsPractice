package com.student.base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class TestBase {

	@BeforeClass
	public static void init(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port=8080;
		RestAssured.basePath="/student";
	}
}
