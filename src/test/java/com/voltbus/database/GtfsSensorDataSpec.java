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

import java.io.InputStream;
import java.net.URI;
import java.time.Instant;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by jacob on 3/14/16.
 */
public class GtfsSensorDataSpec {

    static URI source;
    static URI sink;
    static HttpMethod method;
    static ClientHttpRequestFactory requestFactory;
    static GtfsRealtime.FeedMessage feed;
    static Request request;
    static Response response;
    static byte[] rawData;
    static VoltBinaryRedis voltBinaryRedis;
    static GtfsSensorData sensorData;


    @BeforeClass
    public static void setUp() throws Exception {
        source = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        sink = URI.create("http://localhost:6379");
        method = HttpMethod.GET;
        requestFactory = new SimpleClientHttpRequestFactory();
        request = new VoltHttpRequest(source, method, requestFactory);
        response = request.request();
        rawData = response.responseBody();

        voltBinaryRedis = new VoltBinaryRedis(sink);
        voltBinaryRedis.select(0);
        Instant instant = Instant.now();
        sensorData = GtfsSensorData.newSensorData(instant,
                source, sink);
        feed = sensorData.data();
        GtfsRealtime.FeedMessage.parseFrom(rawData);
    }

    @AfterClass
    public static void tearDown() {
        voltBinaryRedis.flushDB();
        voltBinaryRedis.quit();
    }

    @Test
    public void whenObjectCreatedThenKeyEqualsExpectedResult() {
        String expectedKey = "observations";
        assertThat(sensorData.key(), is(equalTo(expectedKey)));
    }

    @Test
    public void whenSensorDataSavedThenStatusCodeOk() throws Exception {
        byte[] key = sensorData.bytesKey();
        String status = voltBinaryRedis.set(key, feed.toByteArray());
        System.out.println(feed.toString());
        assertThat(status, is(equalTo("OK")));
    }

    @Test
    public void whenSensorObjectCreatedThenSortedSetStoredProperly() throws Exception {
        byte[] key = sensorData.bytesKey();
        Set<byte[]> result = voltBinaryRedis.zrange(key, 0, -1);
        for (byte[] bytes : result) {
            GtfsRealtime.FeedMessage message = GtfsRealtime.FeedMessage.parseFrom(bytes);
            System.out.println(" Data: " + message.toString());
        }
    }

}
