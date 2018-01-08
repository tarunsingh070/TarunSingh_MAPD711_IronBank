package com.example.tarunsingh.tarunsingh_mapd711_ironbank.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tarunsingh on 2018-01-06.
 */

public class Client implements Parcelable {

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

    @SerializedName("currentAddress")
    private String currentAddress;

    @SerializedName("teller")
    private String tellerId;

    @SerializedName("transactions")
    private ArrayList<Transaction> transactionList;

    @SerializedName("accountStatus")
    private String accountStatus;

    @SerializedName("contactInfo")
    private ContactInfo contactInfo;

    public Client() {
    }

    public Client(String firstName, String lastName, String userName, String password, String currentAddress, ContactInfo contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.currentAddress = currentAddress;
        this.contactInfo = contactInfo;
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

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getTellerId() {
        return tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    protected Client(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        userName = in.readString();
        password = in.readString();
        currentAddress = in.readString();
        tellerId = in.readString();
        if (in.readByte() == 0x01) {
            transactionList = new ArrayList<Transaction>();
            in.readList(transactionList, Transaction.class.getClassLoader());
        } else {
            transactionList = null;
        }
        accountStatus = in.readString();
        contactInfo = (ContactInfo) in.readValue(ContactInfo.class.getClassLoader());
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
        dest.writeString(currentAddress);
        dest.writeString(tellerId);
        if (transactionList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(transactionList);
        }
        dest.writeString(accountStatus);
        dest.writeValue(contactInfo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
