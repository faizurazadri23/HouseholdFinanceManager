package com.faizurazadri.householdfinancemanager.Api;

import com.faizurazadri.householdfinancemanager.data.source.remote.response.LoginResponse;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.PostPutDelUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    @FormUrlEncoded
    @POST("Users/registration")
    Call<PostPutDelUserResponse> newUser(@Field("nama_depan") String nama_depan,
                                         @Field("nama_belakang") String nama_belakang,
                                         @Field("username") String username,
                                         @Field("email") String email,
                                         @Field("password") String password,
                                         @Field("alamat") String alamat,
                                         @Field("pekerjaan") String pekerjaan,
                                         @Field("jumlah_anak") String jumlah_anak,
                                         @Field("id_role") String id_role,
                                         @Field("telpn") String telpn
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);
}
