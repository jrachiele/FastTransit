package com.voltbus.database;

import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Set;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.voltbus.network.Request;
import com.voltbus.network.Response;
import com.voltbus.network.VoltHttpRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * Created by jacob on 3/20/16.
 */
public class GtfsSensorData implements SensorData {

    private final FeedMessage data;
    private final VoltBinaryRedis vbRedis;
    private final String key;
    private static final String NAMESPACE = "observations";
    private final long timestamp;

    public static GtfsSensorData newSensorData(Instant instant, URI source, URI destination) {
        return new GtfsSensorData(instant, source, destination);
    }
    private GtfsSensorData(Instant instant, URI source, URI destination) {
        this.timestamp = instant.getEpochSecond();
        Request request = new VoltHttpRequest(
                source, HttpMethod.GET, new SimpleClientHttpRequestFactory());
        Response response = request.request();
        data = retrieveData(response);
        vbRedis = new VoltBinaryRedis(destination);
        this.key = NAMESPACE;
        vbRedis.zadd(bytesKey(), timestamp, data.toByteArray());
    }

    private FeedMessage retrieveData(Response response) {
        try {
            return FeedMessage.parseFrom(response.responseBody());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    String key() {return this.key;}

    byte[] bytesKey() {return this.key.getBytes();}

    public FeedMessage data() {
        return this.data;
    }

}
