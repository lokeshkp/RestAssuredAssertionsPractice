package com.student.logginexamples;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import com.student.base.TestBase;
import com.student.model.Student;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;


public class LoggingRequestValues extends TestBase{
	
	/**
	 *  To log all request headers
	 */
	
	@Test
	public void test01(){
		System.out.println("-----------Printing Request Headers-----------");
		given().log().headers().when().get("/1").then().statusCode(200);
	}
	
	/**
	 *  To log all request parameters
	 */
	@Test
	public void test02(){
		System.out.println("-----------Printing Request Parameters-----------");
		given().param("programme", "Computer Science").log().parameters().when().get("/list").then().statusCode(200);
	}
	
	/**
	 *  To log all request body
	 */
	//@Test
	public void test03(){
		System.out.println("-----------Printing Request Body-----------");
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java3");
		courses.add("Selenium3");
		
		Student st = new Student();
		st.setFirstName("Lokesh3");
		st.setLastName("Kondepudi");
		st.setEmail("lokesh3@gmail.com");
		st.setProgramme("ComputerScience");
		st.setCourses(courses);
		
		given().contentType(ContentType.JSON).log().body().when().body(st).post();
	}
	
	/**
	 *  To log All Request Details
	 */

	@Test
	public void test04(){
		System.out.println("-----------Printing All Request Details-----------");
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java3");
		courses.add("Selenium3");
		
		Student st = new Student();
		st.setFirstName("Lokesh3");
		st.setLastName("Kondepudi");
		st.setEmail("lokesh3@gmail.com");
		st.setProgramme("ComputerScience");
		st.setCourses(courses);
		
		given().contentType(ContentType.JSON).log().all().when().body(st).post();
	}
}
