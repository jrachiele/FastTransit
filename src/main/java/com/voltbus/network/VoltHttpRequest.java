package com.voltbus.network;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Jacob Rachiele on 2/15/16.
 */
public final class VoltHttpRequest implements Request {

    private final URI uri;
    private final ClientHttpRequestFactory requestFactory;

    public VoltHttpRequest(URI uri, ClientHttpRequestFactory requestFactory) {
        this.uri = uri;
        this.requestFactory = requestFactory;
    }

    @Override
    public Response request() {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(uri, byte[].class);
        VoltHttpResponse httpResponse = new VoltHttpResponse(responseEntity);

        return httpResponse;
    }
}
