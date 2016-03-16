package com.voltbus.database;

import org.junit.Test;

import java.time.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jacob on 3/14/16.
 */
public class SensorDataSpec {

    @Test
    public void whenKeyCreatedThenKeyEqualsExpectedResult() {
        Instant instant = Instant.now();
        SensorData sensorData = new SensorData(instant);
        String expectedKey = new StringBuilder("SensorData:").append(instant).toString();
        assertThat(sensorData.key(), is(equalTo(expectedKey)));
    }

}
