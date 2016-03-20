package com.voltbus.database;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Client;

import java.net.URI;
import java.util.Set;

/**
 * Created by jacob on 3/16/16.
 */
public class VoltBinaryRedis {

    private final BinaryJedis jedis;

    public VoltBinaryRedis(URI host) {
        this.jedis = new BinaryJedis(host);
        this.jedis.connect();
    }

    public String select(int index) {
        return jedis.select(index);
    }

    public String flushDB() {
        return jedis.flushDB();
    }

    public String quit() {
        return jedis.quit();
    }

    public String set(byte[] key, byte[] value) {
        return jedis.set(key, value);
    }

    public byte[] get(byte[] key) {
        return jedis.get(key);
    }

    public boolean isConnected() {
        return jedis.isConnected();
    }

    public Long zadd(byte[] key, double score, byte[] member) {
        return jedis.zadd(key, score, member);
    }

    public Set<byte[]> zrange(byte[] key, long start, long end) {
        return jedis.zrange(key, start, end);
    }
}
