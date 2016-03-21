package com.voltbus.network;

/**
 * Created by jacob on 2/15/16.
 */
public interface Request<T> {

    Response<T> request();
}
