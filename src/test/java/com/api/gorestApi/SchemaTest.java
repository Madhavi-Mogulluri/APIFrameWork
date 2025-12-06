package com.api.gorestApi;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.manager.ConfigManager;
import com.api.pojoClasses.User;
import com.api.utilities.SchemaValidatorUtils;
import com.api.utilities.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SchemaTest extends BaseTest{
	
	
	@BeforeMethod
	public void tokenSetUp() {
		ConfigManager.set("bearer_token", "5bae82255d8dd3e8bb494800c89517bf992e712eb996533c5e5eebea98d2caf2");
	}
	@Test
	public void getallusersSchemaTest() {
		
	Response response = restClient.get(GOREST_BASE_URL, USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);	
	
	Assert.assertTrue(SchemaValidatorUtils.schemaValidator(response, "userJsonscema.json"));
	
	}

	
	@Test
	public void createUserSchemaTest() {
		
		User user = User.builder()
				.name("madhu")
				.email(StringUtils.getRandomEmail())
				.gender("Female")
				.status("active")
				.build();
		
	Response res= 	restClient.post(GOREST_BASE_URL, USERS_ENDPOINT, user, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(SchemaValidatorUtils.schemaValidator(res, "singleUserSchema.json"));
		
		
	}
}
