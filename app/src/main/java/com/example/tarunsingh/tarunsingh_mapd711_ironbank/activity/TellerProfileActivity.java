package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;

public class TellerProfileActivity extends AppCompatActivity {

    private TextView tvFullName;
    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teller_profile);
    }
}
