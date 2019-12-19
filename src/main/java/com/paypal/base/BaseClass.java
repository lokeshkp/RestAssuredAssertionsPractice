package com.paypal.base;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import com.student.base.TestBase;
import com.student.model.Student;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
public class BaseClass {
	
	public static String client_ID  = "AZ4zpjmA12qaXCVrtFsyv4klsjUXDomr9tATZpKg_YTh9Mo6I439wMtb7ufhn5QuZ7i3MPUAMTGEQUqE";
	public static String client_Pwd = "EJNy2R1034TgdMq2kX59c45Zh9qrSuJkuJNQa2QCq88K0G9ZPlvuQqJjKyp1NIIi46FdIY7eRq3FF9OB";
	public static String access_token;
	
	@BeforeClass
	public static void getToken(){
		
		RestAssured.baseURI = "https://api.sandbox.paypal.com"; 
		RestAssured.basePath= "/v1";
		
		access_token = given().param("grant_type", "client_credentials").
				auth().preemptive().basic(client_ID, client_Pwd).
		when().post("/oauth2/token").
		then().extract().path("access_token");
		
		System.out.println(access_token);
	}
}
