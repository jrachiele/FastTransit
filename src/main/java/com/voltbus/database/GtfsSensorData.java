package com.voltbus.database;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.temporal.Temporal;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.voltbus.network.Request;
import com.voltbus.network.Response;
import com.voltbus.network.VoltHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Created by jacob on 3/20/16.
 */
public class GtfsSensorData  {

    private final FeedMessage data;
    private final RedisClient redisClient;
    private final String key;
    private static final String NAMESPACE = "observations";
    private final long timestamp;

    public static final GtfsSensorData newSensorData(Instant instant,
    		Response<byte[]> response, RedisClient redisClient) {
        return new GtfsSensorData(instant, response, redisClient);
    }

    private GtfsSensorData(Instant instant,
    		Response<byte[]> response, RedisClient redisClient) {
        this.timestamp = instant.getEpochSecond();
        data = retrieveData(response);
        this.redisClient = redisClient;
        this.key = NAMESPACE;
        this.redisClient.zadd(bytesKey(), timestamp, data.toByteArray());
    }

    private final FeedMessage retrieveData(Response<byte[]> response) {
        try {
            return FeedMessage.parseFrom((byte[])response.responseBody());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            // empty message
            return FeedMessage.getDefaultInstance();
        }
    }

    final String key() {
        return this.key;
    }

    final byte[] bytesKey() {
        return this.key.getBytes();
    }

    public final FeedMessage data() {
        return this.data;
    }

}
