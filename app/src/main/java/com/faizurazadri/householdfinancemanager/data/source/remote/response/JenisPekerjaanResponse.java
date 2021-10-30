package com.faizurazadri.householdfinancemanager.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class JenisPekerjaanResponse implements Parcelable {

    private String pekerjaan_id;
    private String nama;

    public JenisPekerjaanResponse(){}

    public JenisPekerjaanResponse(String pekerjaan_id, String nama) {
        this.pekerjaan_id = pekerjaan_id;
        this.nama = nama;
    }

    protected JenisPekerjaanResponse(Parcel in) {
        pekerjaan_id = in.readString();
        nama = in.readString();
    }

    public static final Creator<JenisPekerjaanResponse> CREATOR = new Creator<JenisPekerjaanResponse>() {
        @Override
        public JenisPekerjaanResponse createFromParcel(Parcel in) {
            return new JenisPekerjaanResponse(in);
        }

        @Override
        public JenisPekerjaanResponse[] newArray(int size) {
            return new JenisPekerjaanResponse[size];
        }
    };

    public String getPekerjaan_id() {
        return pekerjaan_id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pekerjaan_id);
        parcel.writeString(nama);
    }
}
