package com.students.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.student.base.TestBase;

public class StudentDeleteTest extends TestBase{
	
	/**
	 *  DELETE implementation
	 */
	
	@Test
	public void deleteStudent(){
		given().when().delete("/105").then().statusCode(204);
	}
	
	
	


}
