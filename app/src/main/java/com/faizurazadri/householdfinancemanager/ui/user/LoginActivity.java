package com.faizurazadri.householdfinancemanager.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.householdfinancemanager.MainMenuActivity;
import com.faizurazadri.householdfinancemanager.R;
import com.faizurazadri.householdfinancemanager.databinding.ActivityLoginBinding;
import com.faizurazadri.householdfinancemanager.ui.viewmodel.AccountViewModel;
import com.faizurazadri.householdfinancemanager.utils.LoadingDialog;
import com.faizurazadri.householdfinancemanager.utils.ShowSnackbar;
import com.faizurazadri.householdfinancemanager.viewmodel.ViewModelFactoryAccount;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
    private ShowSnackbar showSnackbar = new ShowSnackbar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        loginBinding.txtPendaftaran.setOnClickListener(new btn_action());
        loginBinding.loginBtn.setOnClickListener(new btn_action());

    }

    class btn_action implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.txt_pendaftaran:
                    startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                    break;

                case R.id.login_btn:

                    if (loginBinding.inputUsername.getText().toString().isEmpty()) {
                        loginBinding.inputUsername.setError(getResources().getString(R.string.required));
                        loginBinding.inputUsername.requestFocus();
                        return;
                    }

                    if (loginBinding.inputPassword.getText().toString().isEmpty()) {
                        loginBinding.inputPassword.setError(getResources().getString(R.string.required));
                        loginBinding.inputPassword.requestFocus();
                        return;
                    }

                    setLoginBinding(loginBinding.inputUsername.getText().toString(), loginBinding.inputPassword.getText().toString());
                    break;
            }
        }
    }

    private void setLoginBinding(String username, String password) {
        ViewModelFactoryAccount factoryAccount = ViewModelFactoryAccount.getInstance(this);
        AccountViewModel accountViewModel = new ViewModelProvider(this, factoryAccount).get(AccountViewModel.class);

        accountViewModel.setUsername(username);
        accountViewModel.setPassword(password);

        accountViewModel.login().observe(this, loginResponse -> {
            if (loginResponse.isStatus()) {

                String json = new Gson().toJson(loginResponse.getUser());
                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor =sp.edit();
                editor.putString("data_user", json);
                editor.commit();
                Intent login = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(login);
                finish();
            } else {
                showSnackbar.showSnackbarMessage(loginBinding.getRoot(), loginResponse.getMessage());
            }
        });

        accountViewModel.isError().observe(this,aBoolean -> {
            if (!aBoolean){
                showSnackbar.showSnackbarMessage(loginBinding.getRoot(), "Tidak Ada Response");
            }
        });

    }
}