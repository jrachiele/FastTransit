package com.voltbus.network;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import java.io.IOException;
import java.net.URI;

/**
 * Created by jacob on 2/15/16.
 */
public class SpringHttpRequest implements Request {

    private final VoltClientHttpRequest request;
    private final URI uri;
    private final HttpMethod method;
    private final ClientHttpRequestFactory requestFactory;
    private final Response response;

    public SpringHttpRequest(URI uri, HttpMethod method, Response response,
                             ClientHttpRequestFactory requestFactory) throws IOException {
        this.response = response;
        this.uri = uri;
        this.method = method;
        this.requestFactory = requestFactory;
        this.request = (VoltClientHttpRequest) requestFactory.createRequest(uri, method);
    }

    @Override
    public Response request() {
        SpringClientHttpResponse httpResponse = null;
        try {
            ClientHttpResponse clientHttpResponse = request.execute();
            httpResponse = new SpringClientHttpResponse(clientHttpResponse);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
        return httpResponse;
    }
}
