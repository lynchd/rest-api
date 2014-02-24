package ie.dit.users.integration;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import ie.dit.users.model.User;

import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

public class UserResourceTest 
{
	private static final String USER_PATH = "users-1.0/user";
	private static final String LOGIN_PATH = "users-1.0/login";
	
	@Test
	public void test_createUser_givenValidUser_returnsOk() throws Exception
	{
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.CREATE_USER);
		String newId = UUID.randomUUID().toString();
		request = request.replace("?", newId);
		
		given()
		   	.request().body(request)
		   			  .contentType(MediaType.APPLICATION_JSON)
		.expect()
			.response().statusCode(Response.Status.OK.getStatusCode())
 	   	.when()
 	   		.put(USER_PATH);
	}
	
	@Test
	public void test_createUser_userExists_returnsConflict() throws Exception
	{
		String request = JSON.marshallToString(User.getStockUser());
		
		given()
		   	.request().body(request)
		   			  .contentType(MediaType.APPLICATION_JSON)
		.expect()
			.response().statusCode(Response.Status.CONFLICT.getStatusCode())
 	   	.when()
 	   		.put(USER_PATH).body().asString();	
	}
	
	@Test
	public void test_createUser_givenMalformedUser_returnsBadRequest() throws Exception
	{
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.CREATE_USER_MALFORMED);
			
		given()
		   	.request().body(request)
		   			  .contentType(MediaType.APPLICATION_JSON)
		.expect()
			.response().statusCode(Response.Status.BAD_REQUEST.getStatusCode())
 	   	.when()
 	   		.put(USER_PATH).body().asString();	
	}
	
	@Test
	public void test_getUser_getStockUser_returnsOKAndStockUser() throws Exception
	{
		String token = getStockAuthToken();
		
		String response = given()
	   		.request()
	   			.contentType(MediaType.APPLICATION_JSON)
	   			.header("AuthToken", token)
	   	.expect()
	   		.response().statusCode(Response.Status.OK.getStatusCode())
	   	.when()
	   		.get(USER_PATH + "/" + User.getStockUser().getId()).body().asString();	
		
		User user = (User)JSON.unmarshall(response, User.class);
		
		assertTrue(User.getStockUser().areEqual(user));
	}
	
	@Test
	public void test_updateUser_validUpdate_returnsOK() throws Exception
	{
		String token = getStockAuthToken();
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.UPDATE_USER);
		request = request.replace("?", "1");
		
		given()
	   		.request().body(request)
	   			  .contentType(MediaType.APPLICATION_JSON)
	   			  .header("AuthToken", token).
	    expect().response().statusCode(Response.Status.OK.getStatusCode())
	   	.when()
	   		.post(USER_PATH).body().asString();	
	}
		
	private String getStockAuthToken() throws Exception 
	{
		String request = CannedJSONUtil.loadJSON(CannedJSONUtil.LOGIN);
		
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
		
		return token;
	}
}
