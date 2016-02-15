package com.voltbus.network;

import com.sun.deploy.util.SessionState;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Created by jacob on 2/15/16.
 */
public class SpringClientHttpResponse implements Response {

    private final ClientHttpResponse response;

    public SpringClientHttpResponse(ClientHttpResponse response) {
        this.response = response;
    }
}
