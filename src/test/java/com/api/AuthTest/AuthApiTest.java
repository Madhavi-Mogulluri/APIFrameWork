package com.api.AuthTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthApiTest extends BaseTest{
	@Test
	public void basicAuthTest() {
		
	Response res= 	restClient.get(HEROKU_BASE_URL, HEROKU_BASIC_AUTH_ENDPOINT, null, null, AuthType.BASIC,ContentType.ANY);
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertTrue(res.getBody().asString().contains(" Congratulations! You must have the proper credentials."));
	
	
	}
	
	
	
	

}
