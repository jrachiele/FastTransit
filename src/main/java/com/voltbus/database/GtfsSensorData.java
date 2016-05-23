package com.voltbus.database;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;

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

    public static final GtfsSensorData newSensorData(Instant instant, URI source, URI destination,
    		Request<byte[]> request, RedisClient redisClient) {
        return new GtfsSensorData(instant, source, destination, request, redisClient);
    }

    private GtfsSensorData(Instant instant, URI source, URI destination,
    		Request<byte[]> request, RedisClient redisClient) {
        this.timestamp = instant.getEpochSecond();
        Response<byte[]> response = request.request();
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
