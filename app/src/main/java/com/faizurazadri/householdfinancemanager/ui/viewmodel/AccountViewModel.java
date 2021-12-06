package com.faizurazadri.householdfinancemanager.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.faizurazadri.householdfinancemanager.data.source.remote.response.LoginResponse;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.PostPutDelUserResponse;
import com.faizurazadri.householdfinancemanager.data.source.repository.AccountRepository;

public class AccountViewModel extends ViewModel {

    private AccountRepository accountRepository;
    private String nama_depan;
    private String nama_belakang;
    private String username;
    private String email;
    private String password;
    private String alamat;
    private String pekerjaan;
    private String jumlah_anak;
    private String id_role;
    private String telpn;

    public void setNama_depan(String nama_depan) {
        this.nama_depan = nama_depan;
    }

    public void setNama_belakang(String nama_belakang) {
        this.nama_belakang = nama_belakang;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public void setJumlah_anak(String jumlah_anak) {
        this.jumlah_anak = jumlah_anak;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public void setTelpn(String telpn) {
        this.telpn = telpn;
    }

    public AccountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public LiveData<PostPutDelUserResponse> createUser() {
        return accountRepository.newuser(nama_depan, nama_belakang, username, email, password, alamat, pekerjaan, jumlah_anak, id_role, telpn);
    }

    public LiveData<LoginResponse> login(){
        return accountRepository.login(username, password);
    }

    public LiveData<Boolean> isError(){
        return accountRepository.isError();
    }
}
