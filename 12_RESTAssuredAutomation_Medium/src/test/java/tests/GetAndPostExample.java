package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetAndPostExample {
	
	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		given().
			get("/users?page=2").
		then().
			statusCode(200).
		body("data[4].first_name", equalTo("George")).
		body("data.first_name", hasItems("George", "Tobias","Lindsay"));
	
	}

	@Test
	public void testPost() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Milind");
		map.put("Job", "Team Lead");
		System.out.println(map); 
		
		JSONObject request = new JSONObject(map);
		System.out.println(request.toJSONString());
		
		
	}
	
	@Test
	public void testPost2()  {
		//We can use below method as well to use key value for JSON using JSONObject method,
		// rather than using the above code. 
		
		JSONObject request = new JSONObject();
		request.put("name", "Samarth");
		request.put("Job", "Developer");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
		log().all();
		
		
	}
	
	
}
