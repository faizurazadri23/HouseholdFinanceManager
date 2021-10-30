package com.faizurazadri.householdfinancemanager.utils;

import android.content.Context;

import com.faizurazadri.householdfinancemanager.data.source.remote.response.JenisPekerjaanResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<JenisPekerjaanResponse> loadJenisPekerjaan(){
        ArrayList<JenisPekerjaanResponse> pekerjaanResponseArrayList = new ArrayList<>();

        try {
            String json = parsingFileToString("JenisPekerjaan.json");
            if (json != null){
                JSONObject responseObject = new JSONObject(json);
                JSONArray lisArray = responseObject.getJSONArray("pekerjaan");

                for (int i=0; i < lisArray.length(); i++){

                    JSONObject pekerjaan = lisArray.getJSONObject(i);

                    String pekerjaan_id = pekerjaan.getString("pekerjaan_id");
                    String nama = pekerjaan.getString("nama");

                    JenisPekerjaanResponse jenisPekerjaanResponse = new JenisPekerjaanResponse(pekerjaan_id, nama);
                    pekerjaanResponseArrayList.add(jenisPekerjaanResponse);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return pekerjaanResponseArrayList;
    }

}
