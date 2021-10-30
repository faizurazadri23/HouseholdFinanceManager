package com.faizurazadri.householdfinancemanager.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.faizurazadri.householdfinancemanager.MainMenuActivity;
import com.faizurazadri.householdfinancemanager.R;
import com.faizurazadri.householdfinancemanager.databinding.ActivityLoginBinding;
import com.faizurazadri.householdfinancemanager.utils.LoadingDialog;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        if (getSupportActionBar()!=null){
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

                    if (loginBinding.inputEmail.getText().toString().isEmpty()) {
                        loginBinding.inputEmail.setError(getResources().getString(R.string.required));
                        loginBinding.inputEmail.requestFocus();
                        return;
                    }

                    if (loginBinding.inputPassword.getText().toString().isEmpty()) {
                        loginBinding.inputPassword.setError(getResources().getString(R.string.required));
                        loginBinding.inputPassword.requestFocus();
                        return;
                    }

                    setLoginBinding(loginBinding.inputEmail.getText().toString(), loginBinding.inputPassword.getText().toString());
                    break;
            }
        }
    }

    private void setLoginBinding(String email, String password) {
        Intent login = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(login);
        finish();
    }
}