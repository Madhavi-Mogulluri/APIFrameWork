package com.api.utilities;

import com.api.exceptions.APIExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ObjetMapperUitils {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T> T deSerialization(Response response,Class<T> targetClass) {
		try {
			return objectMapper.readValue(response.getBody().asString(), targetClass);
		}
		catch(Exception e) {
			throw new APIExceptions("deserialization is faile for the " + targetClass);
		}
	}
}
