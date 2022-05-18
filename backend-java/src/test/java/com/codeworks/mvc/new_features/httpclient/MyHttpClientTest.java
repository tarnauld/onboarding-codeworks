package com.codeworks.mvc.new_features.httpclient;

import com.codeworks.mvc.new_features.httpclient.MyHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MyHttpClientTest {
    MyHttpClient myHttpClient;

    @Before
    public void setUp() {
        myHttpClient = new MyHttpClient();
    }

    @Test(expected = IOException.class)
    public void should_post() throws IOException, InterruptedException {
       myHttpClient.post("http://random-server.org", "");
    }
}
