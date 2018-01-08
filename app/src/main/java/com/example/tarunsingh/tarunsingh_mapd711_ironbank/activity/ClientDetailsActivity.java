package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.miscelleneous.Utility;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.RetrofitInstance;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.TellerResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientDetailsActivity extends AppCompatActivity {

    private static final String TAG = ClientDetailsActivity.class.getSimpleName();

    public static final String KEY_TELLER_ID = "tellerId";
    public static final String KEY_CLIENT_ID = "clientId";

    private String tellerId;
    private String clientId;
    private Client client;

    private TextView tvFullName;
    private TextView tvUserName;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvAddress;
    private TextView tvAccountStatus;
    private Button viewTransactionsButton;
    private Button terminateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setDisplayShowHomeEnabled(true);

        init();

        tellerId = getIntent().getStringExtra(KEY_TELLER_ID);
        clientId = getIntent().getStringExtra(KEY_CLIENT_ID);

        if (!(TextUtils.isEmpty(tellerId) || TextUtils.isEmpty(clientId))) {
            fetchClientDetails();
        } else {
            Toast.makeText(this, R.string.invalid_teller_id, Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        tvFullName = findViewById(R.id.tv_fullname);
        tvUserName = findViewById(R.id.tv_username);
        tvEmail = findViewById(R.id.tv_email);
        tvPhone = findViewById(R.id.tv_phone);
        tvAddress = findViewById(R.id.tv_address);
        tvAccountStatus = findViewById(R.id.tv_account_status);

        viewTransactionsButton = findViewById(R.id.button_transactions);
        terminateAccountButton = findViewById(R.id.button_terminate);

        viewTransactionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDetailsActivity.this, TransactionsListActivity.class);
                intent.putParcelableArrayListExtra(TransactionsListActivity.KEY_TRANSACTIONS_LIST, client.getTransactionList());
                startActivity(intent);
            }
        });

        terminateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(R.string.terminate_account_title)
                .setMessage(R.string.terminate_account_message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // terminate account
                        terminateAccount();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void terminateAccount() {
        Utility.showProgress(true, this);

        //Create handle for the RetrofitInstance interface
        TellerResource service = RetrofitInstance.getRetrofitInstance().create(TellerResource.class);
        // Make a rest call to fetch all associated clients with currently logged in teller.
        Call<Client> call = service.terminateClientAccount(tellerId, clientId);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response == null) {
                    return;
                }
                client = response.body();
                Toast.makeText(ClientDetailsActivity.this, R.string.account_deleted_success, Toast.LENGTH_SHORT).show();
                Utility.showProgress(false, ClientDetailsActivity.this);
                finish();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Utility.showProgress(false, ClientDetailsActivity.this);
                Log.e(TAG, t.getMessage());
                Toast.makeText(ClientDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fetchClientDetails() {
        Utility.showProgress(true, this);

        //Create handle for the RetrofitInstance interface
        TellerResource service = RetrofitInstance.getRetrofitInstance().create(TellerResource.class);
        // Make a rest call to fetch all associated clients with currently logged in teller.
        Call<Client> call = service.getClientDetails(tellerId, clientId);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.body() == null) {
                    return;
                }
                client = response.body();
                populateInfo();
                Utility.showProgress(false, ClientDetailsActivity.this);
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Utility.showProgress(false, ClientDetailsActivity.this);
                Log.e(TAG, t.getMessage());
                Toast.makeText(ClientDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateInfo() {
        tvFullName.setText(getString(R.string.full_name, client.getFirstName(), client.getLastName()));
        tvUserName.setText(client.getUserName());
        // FixMe : Contact info is currently not coming sometimes in response.
        if (client.getContactInfo() != null) {
            tvEmail.setText(client.getContactInfo().getEmail());
            tvPhone.setText(client.getContactInfo().getTelephone());
        }
        tvAddress.setText(client.getCurrentAddress());
        tvAccountStatus.setText(client.getAccountStatus());
    }
}
