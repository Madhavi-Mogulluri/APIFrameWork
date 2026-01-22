package com.api.mocking;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class APIMocks {


    public static void defineMockForGetcall(){

        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\n" +
                                "    \"id\": 8326939,\n" +
                                "    \"name\": \"Rowena.Hickle81\",\n" +
                                "    \"email\": \"Dorthy_Braun95@example.com\",\n" +
                                "    \"gender\": \"male\",\n" +
                                "    \"status\": \"active\"\n" +
                                "}")));



    }

    public static void defineMockForGetcallwithFile(){

        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("mockuser.json")));



    }


    public static void defineMockWithQueryParam() {

        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("name", equalTo("sinha"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("mockuser.json")));
    }

    public static void defineMockForPost(){
        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type",equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withBodyFile("mockuser.json")));
    }


    public static void defineMockForPut() {

        stubFor(put(urlPathEqualTo("/api/users"))
                .withQueryParam("name", equalTo("sinha"))
                .withHeader("Content-Type",equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("mockuser.json")
                        .withStatusMessage("user updated Successfully")));


    }






}
