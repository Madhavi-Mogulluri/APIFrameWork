package com.api.gorestApi;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.manager.ConfigManager;
import com.api.pojoClasses.User;
import com.api.utilities.ObjetMapperUitils;
import com.api.utilities.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserwithDeserialization extends BaseTest {
	
	@BeforeTest
	public void tokenSetUp() {
		ConfigManager.set("bearer_token", "5bae82255d8dd3e8bb494800c89517bf992e712eb996533c5e5eebea98d2caf2");
	}
	
	@Test
	public void getUserResponseTest() {
		
		User user = new User(null,"simgha",StringUtils.getRandomEmail(),"male","inactive");
		
		Response res = restClient.post(GOREST_BASE_URL, USERS_ENDPOINT, user, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		String userId = res.jsonPath().getString("id");
		
		Response resGet = restClient.get(GOREST_BASE_URL, USERS_ENDPOINT +"/"+ userId, null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
	User userRes =	ObjetMapperUitils.deSerialization(resGet, User.class);
	
	Assert.assertEquals(user.getName(), userRes.getName());
	
	}

}
