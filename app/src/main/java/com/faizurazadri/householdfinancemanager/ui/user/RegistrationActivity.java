package com.faizurazadri.householdfinancemanager.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.householdfinancemanager.R;
import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;
import com.faizurazadri.householdfinancemanager.databinding.ActivityRegistrationBinding;
import com.faizurazadri.householdfinancemanager.utils.LoadingDialog;
import com.faizurazadri.householdfinancemanager.viewmodel.ViewModelFactoryJenisPekerjaan;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding registrationBinding;
    private LoadingDialog loadingDialog = new LoadingDialog(RegistrationActivity.this);
    private String jenis_pekerjaan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(registrationBinding.getRoot());

        getJenisPekerjaan();
    }

    private void getJenisPekerjaan(){
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