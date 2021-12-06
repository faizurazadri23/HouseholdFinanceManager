package com.faizurazadri.householdfinancemanager.data.source.remote;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.faizurazadri.householdfinancemanager.Api.ApiConfig;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.LoginResponse;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.PostPutDelUserResponse;
import com.faizurazadri.householdfinancemanager.utils.EspressoIdlingResource;
import com.faizurazadri.householdfinancemanager.utils.LoadError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteAccount {

    public static RemoteAccount INSTANCE;
    private ApiConfig apiConfig;

    private Handler handler = new Handler(Looper.myLooper());

    private final long SERVICE_LATENCY_IN_MILLIS = 1000;

    public RemoteAccount(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public static RemoteAccount getInstance(ApiConfig config) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteAccount(config);
        }

        return INSTANCE;
    }

    public void NewUser(String nama_depan, String nama_belakang, String username,
                        String email, String password, String alamat, String pekerjaan, String jumlah_anak, String id_role, String telpn, LoadAccount loadAccount, LoadError error) {
        EspressoIdlingResource.increment();
        Call<PostPutDelUserResponse> newUser = apiConfig.getApiService().newUser(nama_depan, nama_belakang, username, email, password, alamat, pekerjaan, jumlah_anak, id_role, telpn);
        newUser.enqueue(new Callback<PostPutDelUserResponse>() {
            @Override
            public void onResponse(Call<PostPutDelUserResponse> call, Response<PostPutDelUserResponse> response) {
                if (response.isSuccessful()) {
                    handler.postDelayed(() -> {
                        loadAccount.onAccount(response.body());
                        EspressoIdlingResource.decrement();
                    }, SERVICE_LATENCY_IN_MILLIS);
                } else {
                    error.onError(false);
                }
            }

            @Override
            public void onFailure(Call<PostPutDelUserResponse> call, Throwable t) {
                error.onError(true);
                Log.e("Error : ", t.getMessage());
            }
        });
    }

    public void Login(String username, String password, LoadLogin login, LoadError error) {
        EspressoIdlingResource.increment();
        Call<LoginResponse> loginResponseCall = apiConfig.getApiService().login(username, password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    handler.postDelayed(() -> {
                        login.onLogin(response.body());
                        EspressoIdlingResource.decrement();
                    }, SERVICE_LATENCY_IN_MILLIS);
                } else {
                    error.onError(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                error.onError(true);
                Log.e("Error : ", t.getMessage());
            }
        });
    }

    public interface LoadAccount {
        void onAccount(PostPutDelUserResponse postPutDelUserResponse);
    }

    public interface LoadLogin {
        void onLogin(LoginResponse loginResponse);
    }
}
