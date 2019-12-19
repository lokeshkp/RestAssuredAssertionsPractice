package com.jsonpath.examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchJsonPathExample {

	static final String APIKEY ="75e3u4sgb2khg673cbv2gjup";
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI 	= "http://api.walmartlabs.com";
		RestAssured.basePath 	= "/v1";
	}
	
	/**
	 * Extract Num Items...
	 */
	
	//@Test
	public void test01(){
		
		int numItems =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("numItems");
		
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted num items are "+ numItems);
		System.out.println("------------------------End of Test------------------------");

	}

	//@Test
	public void test02(){
		
		String itemName =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("query");
		
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Query Value "+ itemName);
		System.out.println("------------------------End of Test------------------------");

	}
	
	//@Test
	public void test03(){
		
		String itemName =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items[0].name");
		
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Items name is "+ itemName);
		System.out.println("------------------------End of Test------------------------");

	}

	//@Test
	public void test04(){	
		Map<String,String> imageEntities =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items[3].imageEntities[0]");
		
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Image Entities Values "+ imageEntities);
		System.out.println("------------------------End of Test------------------------");

	}
	
	//@Test
	public void test05(){	
		List<String> itemValues =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items.name");
		
		for(String itemValue:itemValues){
			if(itemValue.contains("Refurbished")){
				System.out.println("------------------------Starting Test------------------------");
				System.err.println("Extracted Conditional Values "+ itemValue);
				System.out.println("------------------------End of Test------------------------");
			}
		}
	}
	
	//@Test
	public void test06(){	
		List<HashMap<String,Object>> itemValues =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items.findAll{it.name=='Straight Talk Apple iPhone 7 w/32GB Prepaid Phone, Black'}");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Conditional Values "+ itemValues);
		System.out.println("------------------------End of Test------------------------");
	}
	
	//@Test
	public void test07(){	
		List<String> itemValues =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items.findAll{it.salePrice<150}.name");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Less than $150.. "+ itemValues);
		System.out.println("------------------------End of Test------------------------");
	}
	
	//@Test
	public void test08(){	
		List<String> msrp =given().
			contentType(ContentType.JSON).
			param("query", "iphone").
			param("apiKey", APIKEY).
			param("format", "json").
		when().
			get("/search").
		then().extract().path("items.findAll{it.name==~/Ref.*/}.msrp");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted item that are starts with Ref.. "+ msrp);
		System.out.println("------------------------End of Test------------------------");
	}
	
	@Test
		public void test09(){	
			List<String> salePrice =given().
				contentType(ContentType.JSON).
				param("query", "iphone").
				param("apiKey", APIKEY).
				param("format", "json").
			when().
				get("/search").
			then().extract().path("items.findAll{it.name==~/.*Gray/}.salePrice");
			System.out.println("------------------------Starting Test------------------------");
			System.err.println("Extracted item that are starts with Ref.. "+ salePrice);
			System.out.println("------------------------End of Test------------------------");
		}
}
