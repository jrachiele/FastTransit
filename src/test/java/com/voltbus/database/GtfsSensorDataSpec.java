package com.voltbus.database;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.Position;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.voltbus.network.Request;
import com.voltbus.network.Response;
import com.voltbus.network.VoltHttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

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

    private static URI source;
    private static URI sink;
    private static ClientHttpRequestFactory requestFactory;
    private static GtfsRealtime.FeedMessage feed;
    private static Request<byte[]> request;
    private static Response<byte[]> response;
    private static byte[] rawData;
    private static RedisClient redisClient;
    private static GtfsSensorData sensorData;


    @BeforeClass
    public static void setUp() throws Exception {
        source = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
        sink = URI.create("http://localhost:6379");
        requestFactory = new SimpleClientHttpRequestFactory();
        request = new VoltHttpRequest<>(source, requestFactory, byte[].class);
        response = request.request();
        rawData = response.responseBody();

        redisClient = new VoltBinaryRedis(sink, 15);
        Instant instant = Instant.now();
        sensorData = GtfsSensorData.newSensorData(instant, response, redisClient);
        feed = sensorData.data();
    }

    @AfterClass
    public static void tearDown() {
        redisClient.flushDB();
        redisClient.quit();
    }

    @Test
    public void whenObjectCreatedThenKeyEqualsExpectedResult() {
        String expectedKey = "observations";
        assertThat(sensorData.key(), is(equalTo(expectedKey)));
    }

    @Test
    public void whenSensorDataSavedThenStatusCodeOk() throws Exception {
        byte[] key = sensorData.bytesKey();
        String status = redisClient.set(key, feed.toByteArray());
        System.out.println(feed.toString());
        assertThat(status, is(equalTo("OK")));
    }

    @Test
    public void whenSensorObjectCreatedThenSortedSetStoredProperly() throws Exception {
        byte[] key = sensorData.bytesKey();
        Set<byte[]> result = redisClient.zrange(key, 0, -1);
        for (byte[] bytes : result) {
            GtfsRealtime.FeedMessage message = GtfsRealtime.FeedMessage.parseFrom(bytes);
            System.out.println(message.toString());
            System.out.println(redisClient.zscore(key, bytes));
        }
    }
    
    @Test
    public void testGtfsMessage() {
    	if (feed.getEntityCount() == 0 || feed == null) {
    		System.out.println("The feed is empty");
    	}
    	else {
    	    FeedEntity entity = feed.getEntity(0);
    	    VehiclePosition vehPosition = entity.getVehicle();
    	}
    	
    }

}
