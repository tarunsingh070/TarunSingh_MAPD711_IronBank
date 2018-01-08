package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.adapter.ClientsRecyclerViewAdapter;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.adapter.TransactionsRecyclerViewAdapter;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Transaction;

import java.util.List;

public class TransactionsListActivity extends AppCompatActivity {

    public static final String KEY_TRANSACTIONS_LIST = "TransactionsListActivity.transactionsList";

    private List<Transaction> transactionList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_list);

        transactionList = getIntent().getParcelableArrayListExtra(KEY_TRANSACTIONS_LIST);

        recyclerView = findViewById(R.id.transactionsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TransactionsRecyclerViewAdapter(this, transactionList));
    }
}
