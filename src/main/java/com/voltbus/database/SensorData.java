package com.voltbus.database;

import java.time.Instant;

/**
 * Created by jacob on 3/14/16.
 */
public class SensorData {

    private final String key;
    private static final String NAMESPACE = "SensorData";
    private final Instant instant;

    public SensorData(Instant instant) {
        this.instant = instant;
        this.key = createKey(instant);
    }

    private String createKey(Instant instant) {
        return new StringBuilder(NAMESPACE).append(":").append(instant).toString();
    }

    String key() {return this.key;}


}
