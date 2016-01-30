package com.velox.voltbus.network;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.URI;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by jacob on 1/29/16.
 */
public class SpringClientRequestSpec {

    @Test
    public void whenRequestMadeThenStatusOK() throws Exception {
        URI uri = URI.create("https://data.texas.gov/download/i5qp-g5fd/application/octet-stream");
        ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        SpringClientRequest request = SpringClientRequest.newSpringClientRequest(uri, HttpMethod.GET,
                factory);
        assertThat(request.getResponse().getRawStatusCode(), is(equalTo(200)));
        ;
    }
}
