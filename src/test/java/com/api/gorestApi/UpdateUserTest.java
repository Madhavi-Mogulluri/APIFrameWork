package com.api.gorestApi;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.pojoClasses.User;
import com.api.utilities.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {
	
	@Test
	public void updateUser() {
		
		//1. create user
		
		String emailId =StringUtils.getRandomEmail();
		
		User user = new User(null,"suresh",emailId,"male","inactive");
		
		Response res =restClient.post(GOREST_BASE_URL, USERS_ENDPOINT,user, AuthType.BEARER_TOKEN, ContentType.JSON);
		
	int userId =	res.jsonPath().getInt("id");
		
		// 2.get same user
		
		Response resGet = restClient.get(GOREST_BASE_URL,USERS_ENDPOINT+"/"+ userId,null,null,AuthType.BEARER_TOKEN,ContentType.JSON);
		
		
		//3. update user
		
		user.setStatus("active");
		user.setName("suresh api");
		
		Response resPut = restClient.put(GOREST_BASE_URL, USERS_ENDPOINT+"/"+ userId, user,null,null, AuthType.BEARER_TOKEN,ContentType.JSON);
		
		resPut.prettyPrint();
		Assert.assertEquals(resPut.statusCode(), 200);
		
		
		
	}

}






















