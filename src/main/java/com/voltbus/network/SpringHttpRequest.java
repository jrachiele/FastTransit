package com.voltbus.network;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.net.URI;

/**
 * Created by jacob on 2/15/16.
 */
public class SpringHttpRequest implements Request {

    private final ClientHttpRequest request;
    private final URI uri;
    private final HttpMethod method;
    private final ClientHttpRequestFactory requestFactory;

    public SpringHttpRequest(URI uri, HttpMethod method,
                             ClientHttpRequestFactory requestFactory) throws IOException {
        this.uri = uri;
        this.method = method;
        this.requestFactory = requestFactory;
        this.request = requestFactory.createRequest(uri, method);
    }

    @Override
    public Response request() {
        SpringHttpResponse httpResponse = null;
        try {
            ClientHttpResponse clientHttpResponse = request.execute();
            httpResponse = new SpringHttpResponse(clientHttpResponse);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
        return httpResponse;
    }
}
