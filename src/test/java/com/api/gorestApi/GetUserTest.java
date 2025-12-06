package com.api.gorestApi;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {

	@Test
	public void getAllUsers() {
		
	Response res = 	restClient.get(GOREST_BASE_URL, USERS_ENDPOINT,
            null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(res.statusCode(),200);
		
		
		
		
	}
	

}
