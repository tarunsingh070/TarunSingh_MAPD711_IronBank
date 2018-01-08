package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tarunsingh on 2018-01-08.
 */

public class ClientBody {

        @SerializedName("firstname")
        private String firstName;

        @SerializedName("lastname")
        private String lastName;

        @SerializedName("login")
        private String userName;

        @SerializedName("password")
        private String password;

        @SerializedName("address")
        private String address;

        @SerializedName("email")
        private String email;

        @SerializedName("telephone")
        private String telephone;

        public ClientBody() {
        }

        public ClientBody(String firstName, String lastName, String userName, String password, String currentAddress
                , String email, String telephone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.password = password;
            this.address = currentAddress;
            this.email = email;
            this.telephone = telephone;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
