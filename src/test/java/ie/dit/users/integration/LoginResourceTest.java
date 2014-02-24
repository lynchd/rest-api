package ie.dit.users.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

public class LoginResourceTest 
{
	private static final String LOGIN_PATH = "users-1.0/login";
	
	@Test
	public void test_login_givenValidLogin_returnsOkAndAuth() throws Exception 
	{
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.LOGIN);
		System.out.println(request);
		String response = 
		given()
		   	.request().body(request)
		   			  .contentType(MediaType.APPLICATION_JSON)
		.expect()
			.response().statusCode(Response.Status.OK.getStatusCode())
 	   	.when()
 	   		.post(LOGIN_PATH).body().asString();
		
		JsonPath jsonPath = new JsonPath(response);
		String token = jsonPath.getString("authToken");
		String id = jsonPath.getString("userId");
		
		assertNotNull(token);
		assertFalse("".equals(token));
		
		assertNotNull(id);
		assertTrue("1".equals(id));
	}
	
	@Test
	public void test_login_givenBadCredentials_returnsNotAuthorised() throws Exception 
	{
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.INVALID_LOGIN);

		given()
		   	.request().body(request)
		   			  .contentType(MediaType.APPLICATION_JSON)
		.expect()
			.response().statusCode(Response.Status.UNAUTHORIZED.getStatusCode())
 	   	.when()
 	   		.post(LOGIN_PATH);
	}
}
