package com.filedownload.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.junit.Test;
import org.testng.Assert;

public class FileDownLoadExample {

	static final String url ="https://chromedriver.storage.googleapis.com/79.0.3945.36/chromedriver_win32.zip";

	@Test
	public void test01(){
	
		//Read the Input file
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"chromedriver_win32.zip");
		long expSize = inputFile.length();
		System.out.println(expSize);
		
		byte[] actSize= given().when().get(url).then().extract().asByteArray();
		System.out.println(actSize.length);
		Assert.assertEquals(expSize, actSize.length);
	}
}
