package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tarunsingh on 2018-01-07.
 */

public class ContactInfo implements Parcelable {

    @SerializedName("email")
    private String email;

    @SerializedName("telephone")
    private String telephone;

    public ContactInfo() {
    }

    public ContactInfo(String email, String telephone) {
        this.email = email;
        this.telephone = telephone;
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


    protected ContactInfo(Parcel in) {
        email = in.readString();
        telephone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(telephone);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContactInfo> CREATOR = new Parcelable.Creator<ContactInfo>() {
        @Override
        public ContactInfo createFromParcel(Parcel in) {
            return new ContactInfo(in);
        }

        @Override
        public ContactInfo[] newArray(int size) {
            return new ContactInfo[size];
        }
    };
}
