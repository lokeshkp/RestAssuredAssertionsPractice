package com.student.logginexamples;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import com.student.base.TestBase;
import com.student.model.Student;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class LoggingResponsetValues extends TestBase{

	/**
	 *  To log all Response Headers
	 */
	@Test
	public void test01(){
		System.err.println("-----------Printing Response Headers-----------");

		given().
			param("programme", "Computer Science").
			param("limit", 1).
		when().
			get("/list").
		then().
			log().
			headers().
			statusCode(200);
	}
	
	/**
	 *  To log all Response Status Line
	 */
	
	@Test
	public void test02(){
		System.err.println("-----------Printing Response Status Line-----------");

		given().
			param("programme", "Computer Science").
			param("limit", 1).
		when().
			get("/list").
		then().
			log().
			status().
			statusCode(200);
	}
	
	/**
	 *  To log all Response Body
	 */
	
	@Test
	public void test03(){
		System.err.println("-----------Printing Response Body-----------");

		given().
			param("programme", "Computer Science").
			param("limit", 1).
		when().
			get("/list").
		then().
			log().
			body().
			statusCode(200);
	}
	
	/**
	 *  To log all Response incase of an error
	 */
	
	@Test
	public void test04(){
		System.err.println("-----------Printing Response error if its fail-----------");

		given().
			param("programme", "Computer Science").
			param("limit", 2).
		when().
			get("/list").
		then().
			log().
			ifError();	
	}
}
