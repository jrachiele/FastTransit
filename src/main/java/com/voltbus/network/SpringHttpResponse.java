package com.voltbus.network;

import org.springframework.http.client.ClientHttpResponse;

/**
 * Created by jacob on 2/15/16.
 */
public class SpringHttpResponse implements Response {

    private final ClientHttpResponse response;

    public SpringHttpResponse(ClientHttpResponse response) {
        this.response = response;
    }
}
