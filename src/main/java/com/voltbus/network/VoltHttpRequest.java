package com.voltbus.network;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Jacob Rachiele on 2/15/16.
 */
public final class VoltHttpRequest<T> implements Request<T> {

    private final Class<T> type;
    private final URI uri;
    private final ClientHttpRequestFactory requestFactory;

    public VoltHttpRequest(URI uri, ClientHttpRequestFactory requestFactory,
                           Class<T> type) {
        this.type = type;
        this.uri = uri;
        this.requestFactory = requestFactory;
    }

    @Override
    public Response<T> request() {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(uri, type);
        Response<T> httpResponse = new VoltHttpResponse<>(responseEntity);

        return httpResponse;
    }
}
