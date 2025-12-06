package com.api.AuthTest;

import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.manager.ConfigManager;

import io.restassured.http.ContentType;

@Test
public class APIKey extends BaseTest {
	
	
		public void geminiAPIKeyTest() {
		ConfigManager.set("apikey", "AIzaSyCnQdFWi9S-4W7qs_DR2uMCt1MrOyH5cF8");
		
		String body = "{\n"
				+ "    \"contents\": [\n"
				+ "      {\n"
				+ "        \"parts\": [\n"
				+ "          {\n"
				+ "            \"text\": \"who is Prime Minister of India?\"\n"
				+ "          }\n"
				+ "        ]\n"
				+ "      }\n"
				+ "    ]\n"
				+ "  }";
		restClient.post(GEMINI_BASE_URL, GEMINI_ENDPOINT, body, AuthType.API_KEY, ContentType.JSON);
	}
		

}
