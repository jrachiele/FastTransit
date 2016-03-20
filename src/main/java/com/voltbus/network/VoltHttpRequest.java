package com.voltbus.network;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Jacob Rachiele on 2/15/16.
 */
public final class VoltHttpRequest implements Request {

    private final ClientHttpRequest request;
    private final URI uri;
    private final HttpMethod method;
    private final ClientHttpRequestFactory requestFactory;

    public VoltHttpRequest(URI uri, HttpMethod method,
                           ClientHttpRequestFactory requestFactory) {
        this.uri = uri;
        this.method = method;
        this.requestFactory = requestFactory;
        this.request = createRequest();
    }

    private ClientHttpRequest createRequest() {
        try {
            return requestFactory.createRequest(uri, method);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    @Override
    public Response request() {
        VoltHttpResponse httpResponse = null;
        try {
            ClientHttpResponse clientHttpResponse = request.execute();
            httpResponse = new VoltHttpResponse(clientHttpResponse);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
        return httpResponse;
    }
}
