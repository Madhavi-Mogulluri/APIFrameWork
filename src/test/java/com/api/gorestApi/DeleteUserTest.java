package com.api.gorestApi;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.pojoClasses.User;
import com.api.utilities.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	
	
	@Test
	public void deleteAUser() {
		String emailId =StringUtils.getRandomEmail();
		
		User user = new User(null,"sukeerthip",emailId,"female","active");
	Response  res =	restClient.post(GOREST_BASE_URL, USERS_ENDPOINT, user, AuthType.BEARER_TOKEN, ContentType.JSON);
	
	int userId = res.jsonPath().getInt("id");
	
		//2.get call
	
	Response  resGet =	restClient.get(GOREST_BASE_URL, USERS_ENDPOINT+"/"+ userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
	Response  resDelete =	restClient.delete(GOREST_BASE_URL, USERS_ENDPOINT+"/"+ userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
	
	Response  resGet2 =	restClient.get(GOREST_BASE_URL, USERS_ENDPOINT+"/"+ userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
	
	Assert.assertEquals(resGet2.statusCode(), 404);
	
	}

}
