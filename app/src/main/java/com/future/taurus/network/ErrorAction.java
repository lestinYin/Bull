package com.future.taurus.network;


public interface ErrorAction<Error extends Throwable> {
    void handle(Error throwable);
}