package com.voltbus.network;

import org.springframework.http.ResponseEntity;

/**
 * Created by jacob on 2/15/16.
 */
final class VoltHttpResponse implements Response {

    private final ResponseEntity<byte[]> response;

    VoltHttpResponse(ResponseEntity<byte[]> response) {
        this.response = response;
    }

    public int statusCode() {
        return response.getStatusCode().value();
    }

    public byte[] responseBody() {
        return response.getBody();

    }
}

