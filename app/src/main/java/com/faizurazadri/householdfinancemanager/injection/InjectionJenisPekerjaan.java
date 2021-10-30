package com.faizurazadri.householdfinancemanager.injection;

import android.content.Context;

import com.faizurazadri.householdfinancemanager.data.source.remote.RemoteJenisPekerjaan;
import com.faizurazadri.householdfinancemanager.data.source.repository.JenisPekerjaanRepository;
import com.faizurazadri.householdfinancemanager.utils.JsonHelper;

public class InjectionJenisPekerjaan {
    public static JenisPekerjaanRepository provideRepositoryJenisPekerjaan(Context context) {

        RemoteJenisPekerjaan remoteJenisPekerjaan = RemoteJenisPekerjaan.getInstance(new JsonHelper(context));

        return JenisPekerjaanRepository.getInstance(remoteJenisPekerjaan);
    }
}
