package com.voltbus.network;

import java.io.IOException;

/**
 * Created by jacob on 2/15/16.
 */
public interface Response {
    int statusCode() throws IOException;
}
