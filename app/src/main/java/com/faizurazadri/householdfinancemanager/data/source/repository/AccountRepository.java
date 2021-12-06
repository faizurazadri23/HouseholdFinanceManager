package com.faizurazadri.householdfinancemanager.data.source.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.faizurazadri.householdfinancemanager.data.source.datasource.AccountDataSource;
import com.faizurazadri.householdfinancemanager.data.source.remote.RemoteAccount;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.LoginResponse;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.PostPutDelUserResponse;

public class AccountRepository implements AccountDataSource {

    private volatile static AccountRepository INSTANCE = null;
    private final RemoteAccount remoteAccount;
    private MutableLiveData<Boolean> isError = new MutableLiveData<>();


    private AccountRepository(@NonNull RemoteAccount remoteAccount) {
        this.remoteAccount = remoteAccount;
    }

    public static AccountRepository getInstance(RemoteAccount remoteAccount) {
        if (INSTANCE == null) {
            synchronized (AccountRepository.class) {
                INSTANCE = new AccountRepository(remoteAccount);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<PostPutDelUserResponse> newuser(String nama_depan, String nama_belakang, String username, String email, String password, String alamat, String pekerjaan, String jumlah_anak, String id_role, String telpn) {
        MutableLiveData<PostPutDelUserResponse> newUser = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        remoteAccount.NewUser(nama_depan, nama_belakang, username, email, password, alamat, pekerjaan, jumlah_anak, id_role, telpn, newUser::postValue, isError::postValue);
        return newUser;
    }

    @Override
    public LiveData<LoginResponse> login(String username, String password) {
        MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
        isError = new MutableLiveData<>();
        remoteAccount.Login(username, password, loginResponse::postValue, isError::postValue);
        return loginResponse;
    }

    @Override
    public LiveData<Boolean> isError() {
        return isError;
    }
}
