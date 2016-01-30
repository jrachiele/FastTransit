package com.velox.voltbus.network;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by jacob on 1/29/16.
 */
public class SpringClientRequest implements Request {

    private final URI uri;
    private final HttpMethod httpMethod;
    private final ClientHttpRequest httpRequest;
    private final ClientHttpRequestFactory factory;

    private SpringClientRequest(URI uri, HttpMethod method, ClientHttpRequestFactory factory)
            throws IOException {
        this.uri = uri;
        this.httpMethod = method;
        this.factory = factory;
        this.httpRequest = factory.createRequest(uri, method);

    }

    public static SpringClientRequest newSpringClientRequest(URI uri, HttpMethod method,
                                                 ClientHttpRequestFactory factory) throws IOException {
        return new SpringClientRequest(uri, method, factory);
    }

    public ClientHttpResponse getResponse() {
        try {
            return httpRequest.execute();
        } catch (IOException ioe) {
            System.out.println("Failure to execute request.");
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
            return null;
        }
    }

}
