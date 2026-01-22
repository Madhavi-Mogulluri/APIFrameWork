package com.api.MockTests;

import com.api.base.BaseTest;
import com.api.constants.AuthType;
import com.api.mocking.APIMocks;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.testng.annotations.Test;

import java.util.Map;

public class APIMockTests  extends BaseTest {


    @Test
    public void getMockUserTest(){

        APIMocks.defineMockForGetcall();
      Response res =  restClient.get(BASE_URL_MOCK,"/api/users",null,null, AuthType.NO_AUTH, ContentType.ANY);
        res.then()
                .assertThat().statusCode(200);


}

    @Test
    public void getMockUserTestwithFile(){

        APIMocks.defineMockForGetcallwithFile();
        Response res =  restClient.get(BASE_URL_MOCK,"/api/users",null,null, AuthType.NO_AUTH, ContentType.ANY);
        res.then()
                .assertThat().statusCode(200);


    }


    @Test
    public void getMockUserTestwithQueryParam(){

        Map<String, String> queryParams = new HashedMap();
        queryParams.put("name","sinha");

        APIMocks.defineMockWithQueryParam();
        Response res =  restClient.get(BASE_URL_MOCK,"/api/users",queryParams,null, AuthType.NO_AUTH, ContentType.ANY);
        res.then()
                .assertThat().statusCode(200);


    }

    @Test
    public void mockUserTestWithPost(){

        APIMocks.defineMockForPost();
        Response res  = restClient.post(BASE_URL_MOCK,"/api/users","user.json",AuthType.NO_AUTH,ContentType.JSON);
        res.then()
                .assertThat().statusCode(201);
    }

    @Test
    public void mockUserTestwithPut(){
        Map<String, String> queryParams = new HashedMap();
        queryParams.put("name","sinha");
        String body = "{\n" +
                "    \"name\": \"Rowena.Hickle81\",\n" +
                "}";
        APIMocks.defineMockForPut();
        Response res = restClient.put(BASE_URL_MOCK,"/api/users?name=sinha",body,null,null,AuthType.NO_AUTH,ContentType.JSON);
        res.then()
                .assertThat().statusCode(200);

    }

}
