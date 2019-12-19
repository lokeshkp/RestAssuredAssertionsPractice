package com.students.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import com.student.base.TestBase;
import com.student.model.Student;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class StudentGetTest extends TestBase{

	
	
	/**
	 *  GET implementation
	 */
	
	@Test
	public void getAllStudentsList(){
		
		Response res = given().
		when().get("/list");
		
		System.err.println(res.body().prettyPrint());
		
		// validate status code
		given().when().get("/list").then().statusCode(200);
	}
	
	//@Test
	public void getStudentInfo(){
		Response res  = given().when().get("/103");
		System.err.println(res.body().prettyPrint());
	}
	
	//@Test
	public void getStudentsFromFA(){
		//Response res = given().when().get("/list?programme=Financial Analysis&limit=5");
		//System.err.println(res.body().prettyPrint());
		
		Response res1 = given().param("programme", "Financial Analysis").param("limit", 2).when().get("/list");
		System.out.println(res1.prettyPeek());
	}
}
