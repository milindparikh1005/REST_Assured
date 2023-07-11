package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import io.restassured.response.Response;
import io.restassured.http.ContentType;


public class TestOnLocalAPI {

	@Test
	public void get() {
		
		baseURI= "http://localhost:3000";
		
		given().
			get("/users").
		then().
			statusCode(200).
		body("[0].firstName", equalTo("Milind")).
		log().all();
			
	}
	
	@Test
	public void post() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Dhaval");
		request.put("lastName", "Patel");
		request.put("subjectID", 2);
		
		baseURI= "http://localhost:3000/";

		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).log().all();
		
	}
	
	
	@Test
	public void put() {
		
		JSONObject request = new JSONObject();
		
		request.put("firstName", "Chiku");
		request.put("lastName", "Patel");
		request.put("subjectID", 1);
		
		baseURI= "http://localhost:3000/";

		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/2").
		then().
			statusCode(200).log().all();
		
	}
	
	
	@Test
	public void Delete() {
		baseURI= "http://localhost:3000/";
		
		when().delete("/users/2").then().statusCode(200);
	}
	
}
