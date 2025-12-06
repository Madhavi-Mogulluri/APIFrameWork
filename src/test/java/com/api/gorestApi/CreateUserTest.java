package com.api.gorestApi;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.manager.ConfigManager;
import com.api.pojoClasses.User;
import com.api.utilities.CsvUtil;
import com.api.utilities.StringUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



@Epic("ep:10, create user")
@Feature("fn:23, create user using post call")
@Story("creating user with file, pojo and using data provider")
@Owner("Madhavi Mogulluri")

public class CreateUserTest extends BaseTest{
	
	@BeforeTest
	public void gorestTokensetUp() {
		ConfigManager.set("bearer_token", "5bae82255d8dd3e8bb494800c89517bf992e712eb996533c5e5eebea98d2caf2");
	}
	
	
	
	
	@Test
	public void createUserwithFile() {
		
	Response res= 	restClient.post(GOREST_BASE_URL,USERS_ENDPOINT,new File("src/test/resources/Jsons/user.json"),
				
				AuthType.BEARER_TOKEN,ContentType.JSON);
		
		Assert.assertEquals(res.statusCode(),201);
		
	}
	@DataProvider
	public Object[][] userData() {
	
		return new Object[][] {
			{"sumit","male","active"},
			{"sansesh","male","inactive"},
			{"sushma","female","active"}
			
		};
	}
	@DataProvider
	public Object[][] csvdata() {
		 
		return CsvUtil.readCsv("userdata");
	}
	
	@Test(dataProvider = "csvdata")
	public void createUserwithPojo(String name,String gender,String status) {
		
		String emailId =StringUtils.getRandomEmail();
		
		User user =User.builder()
							.name(name)
							.email(emailId)
							.gender(gender)
							.status(status)
							.build();
	Response res= 	restClient.post(GOREST_BASE_URL,USERS_ENDPOINT,user,
				
				AuthType.BEARER_TOKEN,ContentType.JSON);
		
		Assert.assertEquals(res.statusCode(),201);
		
	}

}
