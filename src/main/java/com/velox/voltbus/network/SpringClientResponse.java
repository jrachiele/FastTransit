package com.velox.voltbus.network;

/**
 * Created by jacob on 1/28/16.
 */
public class SpringClientResponse implements Response {

    @Override
    public int getStatus() {
        return 200;
    }
}
