package com.voltbus.network;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.URI;

/**
 * Created by Jacob Rachiele on 3/9/16.
 */
public class VoltHttpResponseSpec {

    private static URI uri;
    private static ClientHttpRequestFactory requestFactory;

    @BeforeClass
    public static void setUp() {
        uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        requestFactory = new SimpleClientHttpRequestFactory();
    }

    @Test
    public void whenResponseCreatedThenStatusCodeExists() {
        Request<byte[]> request = new VoltHttpRequest<>(uri, requestFactory, byte[].class);
        Response<byte[]> response = request.request();
        assertThat(response.statusCode(), is(instanceOf(int.class)));
    }
    @Test
    public void whenResponseCreatedThenStatusCodeIs200() {
        Request<byte[]> request = new VoltHttpRequest<>(uri, requestFactory, byte[].class);
        Response<byte[]> response = request.request();
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
