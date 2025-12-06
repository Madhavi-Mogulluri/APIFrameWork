package com.api.fakestore;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.pojoClasses.Product;
import com.api.utilities.ObjetMapperUitils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllProductswithPojo extends BaseTest{
	
	@Test
	public void getAllProducts() {
		
		Response res= 
		
		restClient.get(FAKESTORE_BASE_URL,FAKESTORE_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		
	Product []	allProducts = ObjetMapperUitils.deSerialization(res, Product[].class);
		
		for(Product e :allProducts) {
			
			System.out.println(e.getId());
			System.out.println(e.getTitle());
			System.out.println(e.getRating().getRate());
			System.out.println("===========");
		}
		
	}

}
