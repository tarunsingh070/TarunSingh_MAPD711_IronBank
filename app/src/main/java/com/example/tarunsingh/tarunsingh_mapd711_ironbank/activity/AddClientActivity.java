package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.miscelleneous.Utility;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.ClientBody;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.ContactInfo;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.RetrofitInstance;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.TellerResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientActivity extends AppCompatActivity {

    public static final String KEY_TELLER_ID = "AddClientActivity.tellerId";

    private String tellerId;
    private Button addButton;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etAddress;
    private EditText etEmail;
    private EditText etTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        tellerId = getIntent().getStringExtra(KEY_TELLER_ID);

        init();
    }

    private void init() {
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etAddress = findViewById(R.id.et_address);
        etEmail = findViewById(R.id.et_email);
        etTelephone = findViewById(R.id.et_telephone);

        addButton = findViewById(R.id.button_add_client);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (performValidation()) {
                    ClientBody clientToAdd = new ClientBody(etFirstName.getText().toString(), etLastName.getText().toString()
                    , etUserName.getText().toString(), etPassword.getText().toString()
                    ,etAddress.getText().toString(), etEmail.getText().toString(), etTelephone.getText().toString());

                    addClient(clientToAdd);
                }
            }
        });
    }

    private void addClient(ClientBody clientToAdd) {
        Utility.showProgress(true, this);

        //Create handle for the RetrofitInstance interface
        TellerResource service = RetrofitInstance.getRetrofitInstance().create(TellerResource.class);
        // Make a rest call to fetch all associated clients with currently logged in teller.
        Call<Client> call = service.addClientsByTellerId(tellerId, clientToAdd);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client client = response.body();
                Toast.makeText(AddClientActivity.this, R.string.client_add_success, Toast.LENGTH_SHORT).show();
                Utility.showProgress(false, AddClientActivity.this);
                finish();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Utility.showProgress(false, AddClientActivity.this);
                Toast.makeText(AddClientActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean performValidation() {
        if (TextUtils.isEmpty(etFirstName.getText())
                || TextUtils.isEmpty(etLastName.getText())
                || TextUtils.isEmpty(etUserName.getText())
                || TextUtils.isEmpty(etPassword.getText())
                || TextUtils.isEmpty(etAddress.getText())
                || TextUtils.isEmpty(etEmail.getText())
                || TextUtils.isEmpty(etTelephone.getText())) {
            Toast.makeText(this, "All fields are required !", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
