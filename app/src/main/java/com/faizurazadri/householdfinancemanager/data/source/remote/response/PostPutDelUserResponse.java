package com.faizurazadri.householdfinancemanager.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class PostPutDelUserResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
