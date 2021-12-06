package com.faizurazadri.householdfinancemanager.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.householdfinancemanager.R;
import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;
import com.faizurazadri.householdfinancemanager.databinding.ActivityRegistrationBinding;
import com.faizurazadri.householdfinancemanager.ui.viewmodel.AccountViewModel;
import com.faizurazadri.householdfinancemanager.ui.viewmodel.JenisPekerjaanViewModel;
import com.faizurazadri.householdfinancemanager.utils.LoadingDialog;
import com.faizurazadri.householdfinancemanager.utils.ShowSnackbar;
import com.faizurazadri.householdfinancemanager.viewmodel.ViewModelFactoryAccount;
import com.faizurazadri.householdfinancemanager.viewmodel.ViewModelFactoryJenisPekerjaan;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding registrationBinding;
    private LoadingDialog loadingDialog = new LoadingDialog(RegistrationActivity.this);
    private String jenis_pekerjaan = null;
    private ShowSnackbar showSnackbar = new ShowSnackbar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(registrationBinding.getRoot());

        getJenisPekerjaan();

        registrationBinding.btnRegister.setOnClickListener(view -> {
            if (registrationBinding.inputFirstname.getText().toString().isEmpty()){
                registrationBinding.inputFirstname.setError("Required");
                registrationBinding.inputFirstname.requestFocus();
                return;
            }

            if (registrationBinding.inputLastname.getText().toString().isEmpty()){
                registrationBinding.inputLastname.setError("Required");
                registrationBinding.inputLastname.requestFocus();
                return;
            }

            if (registrationBinding.inputUsername.getText().toString().isEmpty()){
                registrationBinding.inputUsername.setError("Required");
                registrationBinding.inputUsername.requestFocus();
                return;
            }

            if (registrationBinding.inputEmail.getText().toString().isEmpty()){
                registrationBinding.inputEmail.setError("Required");
                registrationBinding.inputEmail.requestFocus();
                return;
            }

            if (registrationBinding.inputPassword.getText().toString().isEmpty()){
                registrationBinding.inputPassword.setError("Required");
                registrationBinding.inputPassword.requestFocus();
                return;
            }

            if (registrationBinding.inputAddress.getText().toString().isEmpty()){
                registrationBinding.inputAddress.setError("Required");
                registrationBinding.inputAddress.requestFocus();
                return;
            }

            if (registrationBinding.inputPekerjaan.getText().toString().isEmpty()){
                registrationBinding.inputPekerjaan.setError("Required");
                registrationBinding.inputPekerjaan.requestFocus();
                return;
            }

            if (registrationBinding.inputJumlahAnak.getText().toString().isEmpty()){
                registrationBinding.inputJumlahAnak.setError("Required");
                registrationBinding.inputJumlahAnak.requestFocus();
                return;
            }
            if (registrationBinding.inputNohp.getText().toString().isEmpty()){
                registrationBinding.inputNohp.setError("Required");
                registrationBinding.inputNohp.requestFocus();
                return;
            }



            CreateUser(registrationBinding.inputFirstname.getText().toString(),
                    registrationBinding.inputLastname.getText().toString(),
                    registrationBinding.inputUsername.getText().toString(),
                    registrationBinding.inputEmail.getText().toString(),
                    registrationBinding.inputPassword.getText().toString(),
                    registrationBinding.inputAddress.getText().toString(),
                    registrationBinding.inputPekerjaan.getText().toString(),
                    registrationBinding.inputJumlahAnak.getText().toString(),
                    "1",
                    registrationBinding.inputNohp.getText().toString());
        });
    }

    private void CreateUser(String nama_depan, String nama_belakang, String username, String email, String password, String alamat, String pekerjaan, String jumlah_anak, String id_role, String telpn) {
        ViewModelFactoryAccount factoryAccount = ViewModelFactoryAccount.getInstance(this);
        AccountViewModel accountViewModel = new ViewModelProvider(this, factoryAccount).get(AccountViewModel.class);

        accountViewModel.setNama_depan(nama_depan);
        accountViewModel.setNama_belakang(nama_belakang);
        accountViewModel.setUsername(username);
        accountViewModel.setEmail(email);
        accountViewModel.setPassword(password);
        accountViewModel.setAlamat(alamat);
        accountViewModel.setPekerjaan(pekerjaan);
        accountViewModel.setJumlah_anak(jumlah_anak);
        accountViewModel.setId_role(id_role);
        accountViewModel.setTelpn(telpn);

        registrationBinding.progressRegister.setVisibility(View.VISIBLE);
        accountViewModel.createUser().observe(this, postPutDelUserResponse -> {
            registrationBinding.progressRegister.setVisibility(View.GONE);
            if (postPutDelUserResponse.getStatus() == 200) {
                showSnackbar.showSnackbarMessage(registrationBinding.getRoot(), postPutDelUserResponse.getMessage());
                finish();
            }else {
                showSnackbar.showSnackbarMessage(registrationBinding.getRoot(), "Permintaan Gagal");
            }
        });

        accountViewModel.isError().observe(this, aBoolean -> {
            registrationBinding.progressRegister.setVisibility(View.GONE);
            if (aBoolean){

            }else {
                showSnackbar.showSnackbarMessage(registrationBinding.getRoot(), "Tidak Ada Response");
            }
        });

    }

    private void getJenisPekerjaan() {
        ViewModelFactoryJenisPekerjaan factoryJenisPekerjaan = ViewModelFactoryJenisPekerjaan.getInstance(this);
        JenisPekerjaanViewModel jenisPekerjaanViewModel = new ViewModelProvider(this, factoryJenisPekerjaan).get(JenisPekerjaanViewModel.class);

        List<JenisPekerjanEntity> jenisPekerjanEntityList = jenisPekerjaanViewModel.getJenisPekerjaan();

        List<String> listJenisPekerjaan = new ArrayList<String>();

        for (int i = 0; i < jenisPekerjanEntityList.size(); i++) {
            listJenisPekerjaan.add(jenisPekerjanEntityList.get(i).getNama());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RegistrationActivity.this, R.layout.support_simple_spinner_dropdown_item, listJenisPekerjaan);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        registrationBinding.inputPekerjaan.setAdapter(arrayAdapter);

        registrationBinding.inputPekerjaan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue = arrayAdapter.getItem(i);
                jenis_pekerjaan = jenisPekerjanEntityList.get(i).getNama();

            }
        });


    }
}