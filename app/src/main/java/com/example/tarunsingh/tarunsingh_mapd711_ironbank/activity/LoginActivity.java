package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.miscelleneous.Utility;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.AuthLogin;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Teller;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.AuthResource;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.RetrofitInstance;

import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String KEY_TELLER = "teller_obj";
    public static final String KEY_CLIENT = "client_obj";

    private static final String ACCOUNT_TELLER = "teller";
    private static final String ACCOUNT_CLIENT = "client";

    private RadioButton rbTeller;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SegmentedGroup segmentedGroup = findViewById(R.id.segmentedControl);
        segmentedGroup.setTintColor(getResources().getColor(R.color.colorAccent));

        rbTeller = findViewById(R.id.rb_Teller);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    public void attemptLogin(View view) {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        // Perform validation.
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.empty_creds_validation_message), Toast.LENGTH_LONG).show();
        }

        //Create handle for the RetrofitInstance interface
        AuthResource service = RetrofitInstance.getRetrofitInstance().create(AuthResource.class);

        Utility.showProgress(true, this);

        if (rbTeller.isChecked()) {
            // Login as teller
            loginAsTeller(service, username, password);
        } else {
            // Login as client
            loginAsClient(service, username, password);
        }
    }

    private void loginAsTeller(AuthResource service, String username, String password) {

        AuthLogin authLogin = new AuthLogin(ACCOUNT_TELLER, username, password);

        // Make a rest call to login as teller.
        Call<Teller> call = service.loginAsTeller(authLogin);

        call.enqueue(new Callback<Teller>() {
            @Override
            public void onResponse(Call<Teller> call, Response<Teller> response) {
                if (response.body() == null) {
                    return;
                }
                Teller teller = response.body();
                Intent intent = new Intent(LoginActivity.this, TellerHomeActivity.class);
                intent.putExtra(KEY_TELLER, teller);
                LoginActivity.this.startActivity(intent);
                Utility.showProgress(false, LoginActivity.this);
                finish();
            }

            @Override
            public void onFailure(Call<Teller> call, Throwable t) {
                Utility.showProgress(false,LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Login failed with code : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginAsClient(AuthResource service, String username, String password) {

        AuthLogin authLogin = new AuthLogin(ACCOUNT_CLIENT, username, password);

        // Make a rest call to login as client.
        Call<Client> call = service.loginAsClient(authLogin);

        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.body() == null) {
                    return;
                }
//                Client client = response.body();
//                Intent intent = new Intent(LoginActivity.this, ClientHomeActivity.class);
//                intent.putExtra(KEY_CLIENT, client);
//                LoginActivity.this.startActivity(intent);
                Utility.showProgress(false, LoginActivity.this);
                finish();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Utility.showProgress(false, LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Login failed with code : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
