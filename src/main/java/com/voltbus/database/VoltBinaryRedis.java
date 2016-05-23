package com.voltbus.database;

import redis.clients.jedis.BinaryJedis;

import java.net.URI;
import java.util.Set;

/**
 * Created by jacob on 3/16/16.
 */
public class VoltBinaryRedis implements RedisClient {

    private final BinaryJedis jedis;

    public VoltBinaryRedis(URI host, int db) {
        this.jedis = new BinaryJedis(host);
        this.jedis.select(db);
        this.jedis.connect();
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#select(int)
	 */
    @Override
	public String select(int index) {
        return jedis.select(index);
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#flushDB()
	 */
    @Override
	public String flushDB() {
        return jedis.flushDB();
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#quit()
	 */
    @Override
	public String quit() {
        return jedis.quit();
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#set(byte[], byte[])
	 */
    @Override
	public String set(byte[] key, byte[] value) {
        return jedis.set(key, value);
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#get(byte[])
	 */
    @Override
	public byte[] get(byte[] key) {
        return jedis.get(key);
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#isConnected()
	 */
    @Override
	public boolean isConnected() {
        return jedis.isConnected();
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#zadd(byte[], double, byte[])
	 */
    @Override
	public Long zadd(byte[] key, double score, byte[] member) {
        return jedis.zadd(key, score, member);
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#zrange(byte[], long, long)
	 */
    @Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
        return jedis.zrange(key, start, end);
    }

    /* (non-Javadoc)
	 * @see com.voltbus.database.Redis#zscore(byte[], byte[])
	 */
    @Override
	public Double zscore(byte[] key, byte[] member) {
        return jedis.zscore(key, member);
    }
}
