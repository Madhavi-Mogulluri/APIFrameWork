package com.api.utilities;


import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;
public class SchemaValidatorUtils {
	
		public static boolean schemaValidator(Response response, String fileName ) {
			
			try {
				response.then()
				.body(matchesJsonSchemaInClasspath(fileName));
				System.out.println("schema validation is passed for " + fileName);
				return true;
			}
			catch(Exception e){
				System.out.println("schema validation is failed for " + fileName);
				return false;
			}
	
			
		}


}
