package com.faizurazadri.householdfinancemanager.data.source.local.entity;

public class JenisPekerjanEntity {

    private String pekerjaan_id;
    private String nama;

    public JenisPekerjanEntity(String pekerjaan_id, String nama) {
        this.pekerjaan_id = pekerjaan_id;
        this.nama = nama;
    }

    public String getPekerjaan_id() {
        return pekerjaan_id;
    }

    public String getNama() {
        return nama;
    }
}
