package com.voltbus.database;

import org.junit.Test;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jacob on 3/16/16.
 */
public class VoltRedisSpec {

    @Test
    public void whenObjectCreatedThenConnectionToRedisEstablished() {
        VoltBinaryRedis redisClient = new VoltBinaryRedis(URI.create("http://localhost:6379"));
        assertTrue(redisClient.isConnected());
    }

}
