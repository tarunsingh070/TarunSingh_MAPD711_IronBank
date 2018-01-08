package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tarunsingh on 2018-01-06.
 */

public class AuthLogin {

    @SerializedName("account")
    private String accountType;

    @SerializedName("login")
    private String userName;

    @SerializedName("password")
    private String password;

    public AuthLogin() {
    }

    public AuthLogin(String accountType, String userName, String password) {
        this.accountType = accountType;
        this.userName = userName;
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
