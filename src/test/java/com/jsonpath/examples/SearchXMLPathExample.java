package com.jsonpath.examples;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.element.NodeChildren;

import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.*;
import java.util.List;

public class SearchXMLPathExample {

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

		String numItems =given().
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").
				then().extract().path("searchresponse.numItems");

		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted num items are: "+ numItems);
		System.out.println("------------------------End of Test------------------------");
	}

	/**
	 * Extract Name of 1st Item...
	 */
	//@Test
	public void test02(){

		String itemName =given().
				contentType(ContentType.JSON).
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").
				then().extract().path("searchresponse.items.item[0].name");

		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted 1st Ietm Name: "+ itemName);
		System.out.println("------------------------End of Test------------------------");

	}

	//@Test
	public void test03(){

		String xmlResponse =given().
				param("query", "iphone").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").asString();

		String imgTitles = with(xmlResponse).getString("searchresponse.items.item[0].imageEntities.imageEntities[0].thumbnailImage");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Item ImageTitles are:"+ imgTitles);
		System.out.println("------------------------End of Test------------------------");

	}

	//@Test
	public void test04(){	
		NodeChildren childNodes =given().
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").
				then().extract().path("searchresponse.items.item");

		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Size of Child Nodes:"+ childNodes.size());
		System.out.println("------------------------End of Test------------------------");

	}

	//@Test
	public void test05(){	
		String xmlResponse = given().
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").asString();
		List<String> names = with(xmlResponse).getList("searchresponse.items.item.name");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Item All Child Names:"+ names);
		System.out.println("------------------------End of Test------------------------");	
	}

	//@Test
	public void test06(){	
		String xmlResponse = given().
				param("query", "iphone").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").asString();
		List<String> salePrice = with(xmlResponse).getList("searchresponse.items.item.findAll{it.name=='Refurbished Apple iPhone 7 32GB, Black - Unlocked LTE'}.salePrice");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Condition Item Price:"+ salePrice);
		System.out.println("------------------------End of Test------------------------");	
	}

	//@Test
	public void test07(){	
		String xmlResponse = given().
				param("query", "iphone").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").asString();
		List<String> salePrice = with(xmlResponse).getList("**.findAll{it.name=='Refurbished Apple iPhone 7 32GB, Black - Unlocked LTE'}.salePrice");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted Condition Item Price by Deep Search:"+ salePrice);
		System.out.println("------------------------End of Test------------------------");	
	}

	//@Test
	public void test08(){	
		List<String> msrp =given().
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").
				then().extract().path("items.findAll{it.name==~/Ref.*/}.msrp");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted item that are starts with Ref.. "+ msrp);
		System.out.println("------------------------End of Test------------------------");
	}

	//@Test
	public void test09(){	
		List<String> salePrice =given().
				param("query", "Nokia").
				param("apiKey", APIKEY).
				param("format", "xml").
				when().
				get("/search").
				then().extract().path("items.findAll{it.name==~/.*Gray/}.salePrice");
		System.out.println("------------------------Starting Test------------------------");
		System.err.println("Extracted item that are starts with Ref.. "+ salePrice);
		System.out.println("------------------------End of Test------------------------");
	}
}
