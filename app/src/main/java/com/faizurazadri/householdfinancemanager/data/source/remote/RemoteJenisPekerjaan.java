package com.faizurazadri.householdfinancemanager.data.source.remote;

import com.faizurazadri.householdfinancemanager.data.source.remote.response.JenisPekerjaanResponse;
import com.faizurazadri.householdfinancemanager.utils.JsonHelper;

import java.util.List;

public class RemoteJenisPekerjaan {

    private static RemoteJenisPekerjaan INSTANCE;
    private JsonHelper jsonHelper;

    private RemoteJenisPekerjaan(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteJenisPekerjaan getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteJenisPekerjaan(helper);
        }
        return INSTANCE;
    }

    public List<JenisPekerjaanResponse> getAllCourses() {
        return jsonHelper.loadJenisPekerjaan();
    }
}
