package com.jsonassert.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import org.testng.Assert;

import io.restassured.RestAssured;

public class JsonAssertExamples {

	@Test
	public void getStudents() throws IOException, JSONException{
		String expValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"file.txt")));
		System.out.println("Expected :"+expValue);
		
		String actValue = RestAssured.given().when().get("http://localhost:8080/student/list").asString();
		System.out.println("Actual:"+actValue);
		
		//Assert.assertEquals(expValue, actValue);
		JSONAssert.assertEquals(expValue, actValue, JSONCompareMode.LENIENT);
	}
	
	@Test
	public void getStudentsStrict() throws IOException, JSONException{
		String expValue = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"file2.txt")));
		System.out.println("Expected :"+expValue);
		
		String actValue = RestAssured.given().when().get("http://localhost:8080/student/list").asString();
		System.out.println("Actual:"+actValue);
		
		//Assert.assertEquals(expValue, actValue);
		JSONAssert.assertEquals(expValue, actValue, JSONCompareMode.STRICT);
	}
}
