package com.voltbus.network;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Created by jacob on 3/9/16.
 */
public class VoltHttpRequestSpec {

    @Test
    public void whenRequestMadeThenResponseReceived() throws IOException {
        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Request request = new VoltHttpRequest(uri, requestFactory);
        Response response = request.request();
        assertThat(response, is(notNullValue()));
    }
}
