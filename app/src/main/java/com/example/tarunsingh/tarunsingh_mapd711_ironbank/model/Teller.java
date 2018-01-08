package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tarunsingh on 2018-01-07.
 */

public class Teller implements Parcelable {
    @SerializedName("_id")
    private String id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("login")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("clients")
    private ArrayList<Client> clientList;

    public Teller() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    protected Teller(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        userName = in.readString();
        password = in.readString();
        if (in.readByte() == 0x01) {
            clientList = new ArrayList<Client>();
            in.readList(clientList, Client.class.getClassLoader());
        } else {
            clientList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(userName);
        dest.writeString(password);
        if (clientList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(clientList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Teller> CREATOR = new Parcelable.Creator<Teller>() {
        @Override
        public Teller createFromParcel(Parcel in) {
            return new Teller(in);
        }

        @Override
        public Teller[] newArray(int size) {
            return new Teller[size];
        }
    };
}