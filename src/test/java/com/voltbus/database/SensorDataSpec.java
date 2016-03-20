package com.voltbus.database;

import com.google.transit.realtime.GtfsRealtime;
import com.voltbus.network.Request;
import com.voltbus.network.Response;
import com.voltbus.network.VoltHttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.BinaryJedis;

import java.io.InputStream;
import java.net.URI;
import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by jacob on 3/14/16.
 */
public class SensorDataSpec {

    static URI uri;
    static HttpMethod method;
    static ClientHttpRequestFactory requestFactory;
    static GtfsRealtime.FeedMessage feed;
    static Request request;
    static Response response;
    static InputStream rawData;
    static BinaryJedis jedis;


    @BeforeClass
    public static void setUp() throws Exception {
        uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        method = HttpMethod.GET;
        requestFactory = new SimpleClientHttpRequestFactory();
        request = new VoltHttpRequest(uri, method, requestFactory);
        response = request.request();
        rawData = response.responseBody();
        feed = GtfsRealtime.FeedMessage.parseFrom(rawData);
        jedis = new BinaryJedis("localhost");
        jedis.select(15);
    }

    @AfterClass
    public static void tearDown() {
        jedis.flushDB();
        jedis.quit();
    }

    @Test
    public void whenObjectCreatedThenKeyEqualsExpectedResult() {
        Instant instant = Instant.now();
        SensorData sensorData = new SensorData(instant);
        String expectedKey = new StringBuilder("SensorData:").append(instant).toString();
        assertThat(sensorData.key(), is(equalTo(expectedKey)));
    }

    @Test
    public void whenSensorDataSavedThenStatusCodeOk() throws Exception {
        Instant instant = Instant.now();
        SensorData sensorData = new SensorData(instant);
        byte[] key = sensorData.bytesKey();
        String status = jedis.set(key, feed.toByteArray());
        assertThat(status, is(equalTo("OK")));
    }

}
