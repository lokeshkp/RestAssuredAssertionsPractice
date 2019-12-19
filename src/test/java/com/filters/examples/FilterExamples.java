package com.filters.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class FilterExamples {

	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	
	public static StringWriter responseWriter;
	public static PrintStream responseCapture;

	private static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";

	@BeforeClass
	public static void init(){
		RestAssured.baseURI 	= "http://api.walmartlabs.com";
		RestAssured.basePath 	= "/v1";
	}

	@Before
	public void beforeEachTest(){
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
		
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter),true);
	}

	@Test
	public void test01(){
		String res=	given().queryParam("query", "ipod").
				queryParam("apiKey", APIKEY).
				queryParam("format", "json").log().all().
				when().get("/search").
				asString();

		//System.out.println(res);

		given().queryParam("query", "ipod").
		queryParam("apiKey", APIKEY).
		queryParam("format", "json").
			filter(new RequestLoggingFilter(requestCapture)).
			filter(new ResponseLoggingFilter(responseCapture)).
		when().get("/search");
		System.err.println(requestWriter.toString());
		System.out.println(responseWriter.toString());


	} 
}
