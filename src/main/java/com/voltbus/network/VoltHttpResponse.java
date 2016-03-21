package com.voltbus.network;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jacob on 2/15/16.
 */
public final class VoltHttpResponse implements Response {

    private final ResponseEntity<byte[]> response;

    public VoltHttpResponse(ResponseEntity<byte[]> response) {
        this.response = response;
    }

    public int statusCode() {
        return response.getStatusCode().value();
    }

    public byte[] responseBody() {
        return response.getBody();

    }
}

