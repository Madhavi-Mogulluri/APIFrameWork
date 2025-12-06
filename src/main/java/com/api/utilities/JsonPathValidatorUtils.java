package com.api.utilities;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtils {
	
	private String getString(Response response) {
		
		return response.getBody().asString();
		
	}
	
	
	public <T> T read(Response response,String jaywayQuery) {
		ReadContext ctx =JsonPath.parse(getString(response));
		return ctx.read(jaywayQuery);
	}
	
	public <T> List<T> readList(Response response,String JaywayQuery){
		ReadContext ctx = JsonPath.parse(getString(response));
		return ctx.read(JaywayQuery);
	}
	
	public <T> List<Map<String,T>> readListofMap(Response response,String jaywayQuery){
		
		ReadContext ctx = JsonPath.parse(getString(response));
		return ctx.read(jaywayQuery);
		
	}

}
