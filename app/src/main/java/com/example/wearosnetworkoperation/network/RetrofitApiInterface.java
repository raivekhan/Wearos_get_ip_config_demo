package com.example.wearosnetworkoperation.network;

import com.example.wearosnetworkoperation.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiInterface {
    @GET("/json") //Here, `json` is the PATH PARAMETER
    Call<ServerResponse> getMyIp();
}
