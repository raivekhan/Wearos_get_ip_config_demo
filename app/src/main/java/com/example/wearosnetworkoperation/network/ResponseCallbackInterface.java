package com.example.wearosnetworkoperation.network;

public interface ResponseCallbackInterface<T> {
    void onSuccess(T data);
    void onError(Throwable th);
}
