package com.api.AuthTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Oauth2Test extends BaseTest{
	
	private String accessToken;
	@BeforeTest
	public void getAccessToken() {
		 Response res =	restClient.post(SPOTIFY_TOKEN_BASE_URL, SPOTIFY_TOKEN_ENDPOINT,
				ConfigManager.get("client_id_spotify"),ConfigManager.get("client_secret_spotify"),ConfigManager.get("grant_type_spotify"),
				
				ContentType.URLENC);
		 
		 accessToken = 	res.jsonPath().getString("access_token");
		 ConfigManager.set("bearer_token", accessToken);
				
	}
	
	@Test
	public void getSpotifyAlbumTest() {
		
	Response response= 	restClient.get(SPOTIFY_ALBUM_BASE_URL, SPOTIFY_ALBUM_ENDPOINT+"/4aawyAB9vmqN3uQ7FjRGTy", null, null, AuthType.BEARER_TOKEN,ContentType.ANY);
		
	Assert.assertEquals(response.statusCode(), 200);
	
		
	}
	
	
}
