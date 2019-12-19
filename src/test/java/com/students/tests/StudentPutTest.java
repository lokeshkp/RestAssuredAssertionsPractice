package com.students.tests;


import java.util.ArrayList;
import static io.restassured.RestAssured.given;

import com.student.base.TestBase;
import com.student.model.Student;

import io.restassured.http.ContentType;

public class StudentPutTest extends TestBase{
	
	/**
	 *   PUT implementation
	 */
	
	//@Test
	public void updateStudent(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java2");
		courses.add("Selenium2");
		courses.add("Cypress");
		
		Student st = new Student();
		st.setFirstName("Amma");
		st.setLastName("Kondepudi");
		st.setEmail("lokesh3@gmail.com");
		st.setProgramme("Computer Science");
		st.setCourses(courses);
			
		given().contentType(ContentType.JSON).when().body(st).put("/103").then().statusCode(200);
	}
	

}
