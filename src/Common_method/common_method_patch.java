package Common_method;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class common_method_patch {
	public static int responsestatuscodeextractor(String baseuri, String resource, String req_body)
	{
        //Declare base URI through arguments
		RestAssured.baseURI = baseuri;
		
		//Configure and return the status code
		int response_statuscode = given().header("Content-Type","application/json").body(req_body).when().patch(resource).
				then().extract().statusCode();
		return response_statuscode;
				
	}
	public static String responsebodyextractor(String baseuri, String resource, String req_body)
	{
      //Declare base URI through arguments
		RestAssured.baseURI = baseuri;
		//Configure and return the response body
		String response_body = given().header("Content-Type","application/json").body(req_body).when().patch(resource).
				then().extract().response().asString();
		return response_body;
		
	}

}
