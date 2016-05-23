package com.voltbus.database;

import java.util.Set;

interface RedisClient extends DataSource {

	String select(int index);

	String flushDB();

	String quit();

	String set(byte[] key, byte[] value);

	byte[] get(byte[] key);

	boolean isConnected();

	Long zadd(byte[] key, double score, byte[] member);

	Set<byte[]> zrange(byte[] key, long start, long end);

	Double zscore(byte[] key, byte[] member);

}