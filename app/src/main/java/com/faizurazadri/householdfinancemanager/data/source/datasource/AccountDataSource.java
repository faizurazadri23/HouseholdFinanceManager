package com.faizurazadri.householdfinancemanager.data.source.datasource;

import androidx.lifecycle.LiveData;

import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.LoginResponse;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.PostPutDelUserResponse;

import java.util.List;

public interface AccountDataSource {

    LiveData<PostPutDelUserResponse> newuser(String nama_depan, String nama_belakang, String username,
                                             String email, String password, String alamat, String pekerjaan, String jumlah_anak, String id_role, String telpn);

    LiveData<LoginResponse> login(String username, String password);
    LiveData<Boolean> isError();
}
