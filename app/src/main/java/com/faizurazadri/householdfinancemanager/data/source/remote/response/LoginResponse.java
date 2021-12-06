package com.faizurazadri.householdfinancemanager.data.source.remote.response;

import com.faizurazadri.householdfinancemanager.data.source.local.entity.User;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private User user;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
