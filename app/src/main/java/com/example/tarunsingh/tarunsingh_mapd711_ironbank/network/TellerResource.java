package com.example.tarunsingh.tarunsingh_mapd711_ironbank.network;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.ClientBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tarunsingh on 2018-01-07.
 */

public interface TellerResource {

    @GET("tellers/{teller_id}/clients")
    Call<List<Client>> getClientsByTellerId(@Path("teller_id") String tellerId);

    @GET("tellers/{teller_id}/requests")
    Call<List<Client>> getClientTerminationRequests(@Path("teller_id") String tellerId);

    @GET("tellers/{teller_id}/clients/{client_id}")
    Call<Client> getClientDetails(@Path("teller_id") String tellerId, @Path("client_id") String clientId);

    @POST("tellers/{teller_id}/clients")
    Call<Client> addClientsByTellerId(@Path("teller_id") String tellerId, @Body ClientBody client);

    @DELETE("tellers/{teller_id}/clients/{client_id}")
    Call<Client> terminateClientAccount(@Path("teller_id") String tellerId, @Path("client_id") String clientId);

}
