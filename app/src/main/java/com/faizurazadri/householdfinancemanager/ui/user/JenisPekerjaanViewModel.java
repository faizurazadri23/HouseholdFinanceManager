package com.faizurazadri.householdfinancemanager.ui.user;

import androidx.lifecycle.ViewModel;

import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;
import com.faizurazadri.householdfinancemanager.data.source.repository.JenisPekerjaanRepository;

import java.util.List;

public class JenisPekerjaanViewModel extends ViewModel {

    private JenisPekerjaanRepository jenisPekerjaanRepository;

    public JenisPekerjaanViewModel(JenisPekerjaanRepository jenisPekerjaanRepository) {
        this.jenisPekerjaanRepository = jenisPekerjaanRepository;
    }

    public List<JenisPekerjanEntity> getJenisPekerjaan(){
        return jenisPekerjaanRepository.getAllDataJenisPekerjaan();
    }
}
