package com.voltbus.database;

final class RedisGtfsSensorData {

	
	private final GtfsSensorData sensorData;
    private final RedisClient redisClient;
    private final String key;
	
	RedisGtfsSensorData(final String namespace, final GtfsSensorData sensorData,
			final RedisClient redisClient) {
		this.sensorData = sensorData;
        this.redisClient = redisClient;
        this.key = namespace;
        this.redisClient.zadd(bytesKey(), sensorData.timestamp(), 
        		sensorData.data().toByteArray());	
	}
	
    final String key() {
        return this.key;
    }
    
    final byte[] bytesKey() {
        return this.key.getBytes();
    }
}
