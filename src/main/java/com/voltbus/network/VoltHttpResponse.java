package com.voltbus.network;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jacob on 2/15/16.
 */
public final class VoltHttpResponse implements Response {

    private final ClientHttpResponse response;

    public VoltHttpResponse(ClientHttpResponse response) {
        this.response = response;
    }

    public int statusCode() throws IOException {
        return response.getRawStatusCode();
    }

    public InputStream responseBody() throws IOException {
        return response.getBody();

    }
}

