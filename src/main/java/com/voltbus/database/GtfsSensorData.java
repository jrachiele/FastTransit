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
class GtfsSensorData  {

    private final FeedMessage data;
    private final VoltBinaryRedis vbRedis;
    private final String key;
    private static final String NAMESPACE = "observations";
    private final long timestamp;

    static GtfsSensorData newSensorData(Instant instant, URI source, URI destination) {
        return new GtfsSensorData(instant, source, destination);
    }

    private GtfsSensorData(Instant instant, URI source, URI destination) {
        this.timestamp = instant.getEpochSecond();
        Request<byte[]> request = new VoltHttpRequest<>(
                source, new SimpleClientHttpRequestFactory(), byte[].class);
        Response response = request.request();
        data = retrieveData(response);
        vbRedis = new VoltBinaryRedis(destination, 15);
        this.key = NAMESPACE;
        vbRedis.zadd(bytesKey(), timestamp, data.toByteArray());
    }

    private FeedMessage retrieveData(Response response) {
        try {
            return FeedMessage.parseFrom((byte[])response.responseBody());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            // empty message
            return FeedMessage.getDefaultInstance();
        }
    }

    String key() {
        return this.key;
    }

    byte[] bytesKey() {
        return this.key.getBytes();
    }

    FeedMessage data() {
        return this.data;
    }

}
