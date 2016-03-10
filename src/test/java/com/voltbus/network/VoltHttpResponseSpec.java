package com.voltbus.network;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Jacob Rachiele on 3/9/16.
 */
public class VoltHttpResponseSpec {

    @Test
    public void whenResponseCreatedThenStatusCodeAvailable() throws IOException {

        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        HttpMethod method = HttpMethod.GET;
        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Request request = new VoltHttpRequest(uri, method, requestFactory);
        Response response = request.request();
        assertThat(response.statusCode(), is(instanceOf(int.class)));
    }

    @Test
    public void whenResponseCreatedThenStatusCodeIs200() throws IOException {
        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        HttpMethod method = HttpMethod.GET;
        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Request request = new VoltHttpRequest(uri, method, requestFactory);
        Response response = request.request();
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
