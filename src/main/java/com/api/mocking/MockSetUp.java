package com.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MockSetUp {

    private static WireMockServer server;


    public static void startWireMockServer(){
        server = new WireMockServer(9099);
        server.start();
        WireMock.configureFor("localhost",9099);
    }


    public static void stopWireMockServer(){
        server.stop();
    }

}
