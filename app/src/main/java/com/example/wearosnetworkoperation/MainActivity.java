package com.example.wearosnetworkoperation;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wearosnetworkoperation.network.MyApiInterface;
import com.example.wearosnetworkoperation.network.NetworkCall;
import com.example.wearosnetworkoperation.network.ResponseCallbackInterface;
import com.example.wearosnetworkoperation.network.RetrofitApiInterface;
import com.example.wearosnetworkoperation.network.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends WearableActivity {

    private TextView ipAddressTextView;
    private TextView cityTextView;
    private TextView countryTextView;
    private ProgressBar progressBar;
    private String Tag = "Network test app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddressTextView = findViewById(R.id.ipAddressTextView);
        cityTextView = findViewById(R.id.cityTextview);
        countryTextView = findViewById(R.id.countryText);
        progressBar = findViewById(R.id.progressBar);

        // Enables Always-on
        setAmbientEnabled();
    }
    public void showMyIp(View view) {

        cityTextView.setVisibility(View.GONE);
        countryTextView.setVisibility(View.GONE);
        ipAddressTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE); //network call will start. So, show progress bar
        MyApiInterface apiService = new NetworkCall();
        apiService.getMyIpConfig(new ResponseCallbackInterface<ServerResponse>() {
            @Override
            public void onSuccess(ServerResponse serverResponse) {
                progressBar.setVisibility(View.GONE); //network call success. So hide progress bar
                cityTextView.setVisibility(View.VISIBLE);
                countryTextView.setVisibility(View.VISIBLE);
                ipAddressTextView.setVisibility(View.VISIBLE);

                Log.d(Tag,"got result 200"  );
                ipAddressTextView.setText(serverResponse.getIp());
                cityTextView.setText(serverResponse.getCity());
                countryTextView.setText(serverResponse.getCountry());
            }

            @Override
            public void onError(Throwable th) {
                Log.d(Tag,"got result other than 200"  );
                progressBar.setVisibility(View.GONE); //network call failed. So hide progress bar
                ipAddressTextView.setVisibility(View.VISIBLE);
                ipAddressTextView.setText(th.getMessage());
                cityTextView.setText("");
                countryTextView.setText("");
            }
        });
    }
}