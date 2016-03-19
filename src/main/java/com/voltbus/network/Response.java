package com.voltbus.network;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jacob on 2/15/16.
 */
public interface Response {
    int statusCode() throws IOException;
    InputStream responseBody() throws IOException;
}
