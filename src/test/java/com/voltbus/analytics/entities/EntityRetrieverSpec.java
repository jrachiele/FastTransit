package com.voltbus.analytics.entities;

import com.google.transit.realtime.GtfsRealtime;
import org.junit.Test;
import com.voltbus.network.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.net.URI;

/**
 * Created by jacob on 3/19/16.
 */
public class EntityRetrieverSpec {

//    @Test
//    public void testGtfsMessage() throws Exception {
//        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
//        HttpMethod method = HttpMethod.GET;
//        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Request request = new VoltHttpRequest(uri, method, requestFactory);
//        Response response = request.request();
//        InputStream rawData = response.responseBody();
//        FeedMessage feed = FeedMessage.parseFrom(rawData);
//        for (FeedEntity entity : feed.getEntityList()) {
//            System.out.println("______________");
//
//        }
//    }
//
//    @Test
//    public void testSettingStops() throws Exception {
//        URI uri = URI.create("https://data.texas.gov/download/eiei-9rpf/application/octet-stream");
//        HttpMethod method = HttpMethod.GET;
//        ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Request request = new VoltHttpRequest(uri, method, requestFactory);
//        Response response = request.request();
//        InputStream rawData = response.responseBody();
//        FeedMessage feed = FeedMessage.parseFrom(rawData);
//        BinaryJedis jedis = new BinaryJedis("localHost");
//        GtfsRealtime.VehiclePosition position = feed.getEntity(0).getVehicle();
//        byte[] stop = ("stop:" + position.getStopId()).getBytes();
//        byte[] time = "time".getBytes();
//        byte[] timestamp = Long.toString(position.getTimestamp()).getBytes();
//        jedis.hset(stop, time, timestamp);
//        System.out.println(new String(jedis.hget(stop,time)));
//    }
}
