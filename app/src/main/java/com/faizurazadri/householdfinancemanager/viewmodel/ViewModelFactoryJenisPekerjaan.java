package com.faizurazadri.householdfinancemanager.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.householdfinancemanager.data.source.repository.JenisPekerjaanRepository;
import com.faizurazadri.householdfinancemanager.injection.InjectionJenisPekerjaan;
import com.faizurazadri.householdfinancemanager.ui.user.JenisPekerjaanViewModel;

public class ViewModelFactoryJenisPekerjaan extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactoryJenisPekerjaan INSTANCE;

    private final JenisPekerjaanRepository mJenisPekerjaanRepo;

    private ViewModelFactoryJenisPekerjaan(JenisPekerjaanRepository jenisPekerjaanRepository) {
        mJenisPekerjaanRepo = jenisPekerjaanRepository;
    }

    public static ViewModelFactoryJenisPekerjaan getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactoryJenisPekerjaan.class) {
                INSTANCE = new ViewModelFactoryJenisPekerjaan(InjectionJenisPekerjaan.provideRepositoryJenisPekerjaan(context));
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(JenisPekerjaanViewModel.class)) {
            return (T) new JenisPekerjaanViewModel(mJenisPekerjaanRepo);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
