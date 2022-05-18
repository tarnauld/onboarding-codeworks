package com.codeworks.mvc.new_features.httpclient;

import java.io.IOException;

public interface IHttpClient {
    String post(String url, String data) throws IOException, InterruptedException;
}
