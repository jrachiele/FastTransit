package com.voltbus.network;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Jacob Rachiele on 3/9/16.
 */
public class VoltHttpResponseSpec {

    private static URI uri;
    private static HttpMethod method;
    private static ClientHttpRequestFactory requestFactory;

    @BeforeClass
    public static void setUp() {
        uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        method = HttpMethod.GET;
        requestFactory = new SimpleClientHttpRequestFactory();
    }

    @Test
    public void whenResponseCreatedThenStatusCodeExists() {
        Request request = new VoltHttpRequest(uri, method, requestFactory);
        Response response = request.request();
        assertThat(response.statusCode(), is(instanceOf(int.class)));
    }
    @Test
    public void whenResponseCreatedThenStatusCodeIs200() {
        Request request = new VoltHttpRequest(uri, method, requestFactory);
        Response response = request.request();
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
