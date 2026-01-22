package com.api.client;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.api.constants.AuthType;
import com.api.exceptions.APIExceptions;
import com.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient {
	
	
	private ResponseSpecification responsespec200or404 =  expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	private ResponseSpecification responsespec200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	
	private ResponseSpecification responsespec204 = expect().statusCode(204);
	
	
	/**
	 * this method used to add basic requirements for CRUD operations and generates request
	 * @param baseUrl
	 * @param authType
	 * @param contentType
	 * @return
	 */
	private RequestSpecification setUpRequest(String baseUrl,AuthType authType,ContentType contentType) {
		
	RequestSpecification request =	given().log().all()
				.contentType(contentType)
				.baseUri(baseUrl)
				.accept(contentType);
	
	
	switch (authType) {
	case BEARER_TOKEN:
		request.header("Authorization","Bearer " + ConfigManager.get("bearer_token"));
		break;
	case BASIC:
		request.header("Authorization","Basic " + generateBasicAuthString());
		break;
	case API_KEY:
		request.header("API_KEY",ConfigManager.get("apikey"));
		break;
	case NO_AUTH:
		System.out.println("auth is not required");
		break;
	default:
		System.out.println("please pass right auth type");
		
		throw new APIExceptions("invlaid auth type");
		
		
	}
	return request ;
	
	}
	/**
	 * This method generates equivalent string by using username and password
	 * @return encoded string of credentials
	 */
	private String generateBasicAuthString() {
		
	String credentials =ConfigManager.get("basicAuth_userName").trim() + ":"+ ConfigManager.get("basicAuth_password").trim();
	return  Base64.getEncoder().encodeToString(credentials.getBytes());
	
	}
	/**
	 * this method helps to append query and path parameters if applicable
	 * @param queryparams
	 * @param pathparams
	 * @param request
	 */
	
	private void applyparams(Map<String,String> queryparams,
			Map<String,String> pathparams,RequestSpecification request) {
		
		if(queryparams != null) {
			request.queryParams(queryparams);
		}

		if(pathparams != null) {
			request.queryParams(pathparams);
		}
		
		
		
		
	}
	
	/**
	 * this method used to get all or single 
	 * @param baseUrl
	 * @param endpoint
	 * @param queryparams
	 * @param pathparams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public Response  get(String baseUrl,String endpoint,Map<String,String> queryparams,
			Map<String,String> pathparams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
		
		applyparams(queryparams,pathparams,request);
		
		Response  response= request.urlEncodingEnabled(false)
							.get(endpoint)
							.then()
							.spec(responsespec200or404)
							.extract()
							.response();
		response.prettyPrint();
	
	return response;
	}
	/**
	 * this method accepts any type of body except file
	 * @param <T>
	 * @param baseUrl
	 * @param endpoint
	 * @param body
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>  Response post(String baseUrl,String endpoint,T body,AuthType authType,ContentType contentType) {
		
		RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
		
		request.urlEncodingEnabled(false).body(body);
		
	Response response =	request.post(endpoint)
					.then().log().all()
					.spec(responsespec200or201)
					.extract()
					.response();
	
	return response;
	}
	/**
	 * this method accepts file type body and creation
	 * @param baseUrl
	 * @param endpoint
	 * @param authType
	 * @param contentType
	 * @return
	 */
	
	public  Response  post(String baseUrl,String endpoint,File file,AuthType authType,ContentType contentType) {
			
		RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
		
		request.urlEncodingEnabled(false).body(file);
		
	Response response =	request.post(endpoint)
					.then().log().all()
					.spec(responsespec200or201)
					.extract()
					.response();
	
	return response;
	}
	/**
	 * this post method is called when user has to provide access token using Oauth2.0
	 * @param baseUrl
	 * @param endpoint
	 * @param clientId
	 * @param clientSecret
	 * @param grantType
	 * @param contentType
	 */
	public   Response post(String baseUrl,String endpoint,String clientId,String clientSecret,String grantType,ContentType contentType) {
		
		
	RequestSpecification request = RestAssured.given()
				.contentType(ContentType.URLENC)
				.formParam("grant_type",grantType)
				.formParam("client_id",clientId )
				.formParam("client_secret", clientSecret);
	
	Response response = request.urlEncodingEnabled(false)
								.post(baseUrl + endpoint);
	
			return response;
	}
	/**
	 * update call
	 * @param <T>
	 * @param baseUrl
	 * @param endpoint
	 * @param body
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>  Response put(String baseUrl,String endpoint,T body,Map<String,String> queryparams,
			Map<String,String> pathparams,AuthType authType,ContentType contentType) {
		
		
		RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
		
		request.urlEncodingEnabled(false).body(body);


	Response response =	request.put(endpoint)
					.then().log().all()
					.spec(responsespec200or404)
					.extract()
					.response();

	
	return response;
	}
	
	/**
	 * update call
	 * @param <T>
	 * @param baseUrl
	 * @param endpoint
	 * @param body
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T>  Response patch(String baseUrl,String endpoint,T body,Map<String,String> queryparams,
			Map<String,String> pathparams,AuthType authType,ContentType contentType) {
		
		
		RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
		
		request.urlEncodingEnabled(false).body(body);
		
	Response response =	request.put(endpoint)
					.then().log().all()
					.spec(responsespec200or404)
					.extract()
					.response();
	return response;
	}
	/**
	 * delete call
	 * @param baseUrl
	 * @param endpoint
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public  Response delete(String baseUrl,String endpoint,Map<String,String> queryparams,
			Map<String,String> pathparams,AuthType authType,ContentType contentType) {
	
	RequestSpecification request = setUpRequest(baseUrl,authType,contentType);
	
	Response response =	request.urlEncodingEnabled(false)
				.delete(endpoint)
				.then().log().all()
				.spec(responsespec204)
				.extract()
				.response();
	return response;
}
	

}
