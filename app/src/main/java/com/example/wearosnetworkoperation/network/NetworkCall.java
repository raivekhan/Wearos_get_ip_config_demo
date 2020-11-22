package com.example.wearosnetworkoperation.network;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.wearosnetworkoperation.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall implements MyApiInterface {
    @Override
    public void getMyIpConfig(final ResponseCallbackInterface<ServerResponse> ipConfigListener) {
        final RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<ServerResponse> call = retrofitApiInterface.getMyIp();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {

                ServerResponse serverResponse = response.body();

                if (response.code()==200 && serverResponse!=null) {
                    ipConfigListener.onSuccess(serverResponse);
                } else {
                    ipConfigListener.onError(new Exception("response code is not 200"));
                }

            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                ipConfigListener.onError(new Exception(t));

            }

        });
    }
}
