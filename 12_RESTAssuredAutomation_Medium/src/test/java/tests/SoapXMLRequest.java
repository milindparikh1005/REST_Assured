package tests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.*;

public class SoapXMLRequest {

	@Test
	public void validateSoapXML() throws IOException {
		
		File file = new File("C:\\QA\\SeleniumWorkSpace\\12_RESTAssuredAutomation_Medium\\SoapRequest\\add.xml");
		FileInputStream fileinput = new FileInputStream(file);
		
		if(file.exists())
			System.out.println(">> FILE EXISTS");
		
		String requestBody = IOUtils.toString(fileinput, "UTF-8");
		
		baseURI= "https://ecs.syr.edu/";
		
		given().
			contentType("text/xml").
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx?op=Add").
		then().
			statusCode(200).
		log().all();
		
		
	}
	
}
