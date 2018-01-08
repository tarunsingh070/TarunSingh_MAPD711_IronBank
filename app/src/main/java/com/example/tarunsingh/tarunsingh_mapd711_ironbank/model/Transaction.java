package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tarunsingh on 2018-01-06.
 */

public class Transaction implements Parcelable {

    @SerializedName("_id")
    private String id;

    @SerializedName("transactionName")
    private String transactionName;

    @SerializedName("transactionDate")
    private String transactionDate;

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    protected Transaction(Parcel in) {
        id = in.readString();
        transactionName = in.readString();
        transactionDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(transactionName);
        dest.writeString(transactionDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}