package com.voltbus.database;

import org.junit.Test;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by jacob on 3/19/16.
 */
public class GtfsSensorDataSpec {

    @Test
    public void whenObjectCreatedThenKeyEqualsExpectedResult() {
        Instant instant = Instant.now();
        SensorData sensorData = new GtfsSensorData(instant);
        String expectedKey = new StringBuilder("SensorData:").append(instant).toString();
        assertThat(sensorData.key(), is(equalTo(expectedKey)));
    }
}
