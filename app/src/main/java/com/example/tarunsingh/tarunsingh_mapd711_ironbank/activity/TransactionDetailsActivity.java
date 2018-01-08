package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDetailsActivity extends AppCompatActivity {

    public static final String KEY_TRANSACTION = "TransactionDetailsActivity.transaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        Transaction transaction = getIntent().getParcelableExtra(KEY_TRANSACTION);

        TextView tvTransactionName = findViewById(R.id.tv_transaction_name);
        TextView tvTransactionDate = findViewById(R.id.tv_transaction_date);
        TextView tvTransactionId = findViewById(R.id.tv_transaction_id);

        tvTransactionName.setText(transaction.getTransactionName());
        tvTransactionDate.setText(getConvertedDate(transaction.getTransactionDate()).toString());
        tvTransactionId.setText(transaction.getId());
    }

    private Date getConvertedDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }
}
