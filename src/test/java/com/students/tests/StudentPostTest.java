package com.students.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.student.base.TestBase;
import com.student.model.Student;

import io.restassured.http.ContentType;

public class StudentPostTest extends TestBase {

	/**
	 *   POST implementation
	 */
	//@Test
	public void createNewStudent(){
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java2");
		courses.add("Selenium2");
		
		Student st = new Student();
		st.setFirstName("Lokesh2");
		st.setLastName("Kondepudi");
		st.setEmail("lokesh2@gmail.com");
		st.setProgramme("ComputerScience2");
		st.setCourses(courses);
		
		given().contentType(ContentType.JSON).when().body(st).post();
		
	}
}
