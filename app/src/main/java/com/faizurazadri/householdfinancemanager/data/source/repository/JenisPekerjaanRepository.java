package com.faizurazadri.householdfinancemanager.data.source.repository;

import androidx.annotation.NonNull;

import com.faizurazadri.householdfinancemanager.data.source.datasource.JenisPekerjaanDataSource;
import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;
import com.faizurazadri.householdfinancemanager.data.source.remote.RemoteJenisPekerjaan;
import com.faizurazadri.householdfinancemanager.data.source.remote.response.JenisPekerjaanResponse;

import java.util.ArrayList;
import java.util.List;

public class JenisPekerjaanRepository implements JenisPekerjaanDataSource {

    private volatile static JenisPekerjaanRepository INSTANCE = null;
    private final RemoteJenisPekerjaan remoteDataSource;


    private JenisPekerjaanRepository(@NonNull RemoteJenisPekerjaan remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
    public static JenisPekerjaanRepository getInstance(RemoteJenisPekerjaan remoteData) {
        if (INSTANCE == null) {
            synchronized (JenisPekerjaanRepository.class) {
                INSTANCE = new JenisPekerjaanRepository(remoteData);
            }
        }
        return INSTANCE;
    }

    @Override
    public List<JenisPekerjanEntity> getAllDataJenisPekerjaan() {
        List<JenisPekerjaanResponse> jenisPekerjaanResponses = remoteDataSource.getAllCourses();
        ArrayList<JenisPekerjanEntity> jenisPekerjanEntityArrayList = new ArrayList<>();
        for (JenisPekerjaanResponse response : jenisPekerjaanResponses){
            JenisPekerjanEntity jenisPekerjaan = new JenisPekerjanEntity(response.getPekerjaan_id()
            ,response.getNama());

            jenisPekerjanEntityArrayList.add(jenisPekerjaan);
        }
        return jenisPekerjanEntityArrayList;
    }
}
