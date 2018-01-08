package com.example.tarunsingh.tarunsingh_mapd711_ironbank.network;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.AuthLogin;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Teller;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tarunsingh on 2018-01-06.
 */

public interface AuthResource {

    @POST("auth")
    Call<Client> loginAsClient(@Body AuthLogin authLogin);

    @POST("auth")
    Call<Teller> loginAsTeller(@Body AuthLogin authLogin);

//    @GET("devices/{id}?embed=timeframes")
//    Call<XMDevice> getDevice(@Path("id") String deviceId);
//
//    @DELETE("devices/{id}")
//    Call<XMDevice> deleteDevice(@Path("id") String deviceId);

}
