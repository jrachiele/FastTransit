package com.voltbus.network;

import org.springframework.http.ResponseEntity;

/**
 * Created by jacob on 2/15/16.
 */
final class VoltHttpResponse<T> implements Response<T> {

    private final ResponseEntity<T> response;

    VoltHttpResponse(final ResponseEntity<T> response) {
        this.response = response;
    }

    @Override
    public final int statusCode() {
        return response.getStatusCode().value();
    }

    @Override
    public final T responseBody() {
        return response.getBody();

    }
}

