package com.example.wearosnetworkoperation.network;

import com.example.wearosnetworkoperation.ServerResponse;

public interface MyApiInterface {
    void getMyIpConfig(ResponseCallbackInterface<ServerResponse> callback);
}
