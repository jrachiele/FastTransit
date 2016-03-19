package com.voltbus.analytics.entities;

import org.junit.Test;
import com.voltbus.network.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by jacob on 3/19/16.
 */
public class GtfsMessageSpec {

    @Test
    public void TestGtfsMessage() throws Exception {
        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        HttpMethod method = HttpMethod.GET;
        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Request request = new VoltHttpRequest(uri, method, requestFactory);
        Response response = request.request();
        InputStream rawData = response.responseBody();
        FeedMessage feed = FeedMessage.parseFrom(rawData);
        for (FeedEntity entity : feed.getEntityList()) {

                System.out.println(entity.toString());

        }
    }
}
