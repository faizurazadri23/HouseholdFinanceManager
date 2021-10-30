package com.faizurazadri.householdfinancemanager.data.source.datasource;

import com.faizurazadri.householdfinancemanager.data.source.local.entity.JenisPekerjanEntity;

import java.util.List;

public interface JenisPekerjaanDataSource {

    List<JenisPekerjanEntity> getAllDataJenisPekerjaan();
}
