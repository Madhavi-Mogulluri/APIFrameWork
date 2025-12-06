package com.api.base;

import com.api.manager.ConfigManager;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.api.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected RestClient restClient;
	
	//******API BASE URLS*********

    protected static  String GOREST_BASE_URL;
	
//	protected static final String GOREST_BASE_URL = "https://gorest.co.in";
	protected static final String TOKEN_ID = "https://gorest.co.in";
	protected static final String HEROKU_BASE_URL ="https://the-internet.herokuapp.com";
	protected static final String SPOTIFY_TOKEN_BASE_URL ="https://accounts.spotify.com";
	protected static final String SPOTIFY_ALBUM_BASE_URL = "https://api.spotify.com";
	protected static final String FAKESTORE_BASE_URL = "https://fakestoreapi.com/";
	protected static final String GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/";

	
	
	
	
	
	//******API ENDPOINT  URLS*********
	
	protected static final String USERS_ENDPOINT = "/public/v2/users";
	protected static final String HEROKU_BASIC_AUTH_ENDPOINT = "/basic_auth";
	protected static final String SPOTIFY_TOKEN_ENDPOINT = "/api/token";
	protected static final String SPOTIFY_ALBUM_ENDPOINT = "/v1/albums";
	protected static final String FAKESTORE_ENDPOINT = "/products";
	protected static final String GEMINI_ENDPOINT = "/v1beta/models/gemini-2.0-flash:generateContent";
	
	
	
	@BeforeTest
	public void setUp() {	
		restClient = new RestClient();
		
	};
	
	@BeforeSuite
	public void initSetUp() {
        GOREST_BASE_URL = ConfigManager.get("baseurl.gorest");
		RestAssured.filters(new AllureRestAssured());
	}
	

}
