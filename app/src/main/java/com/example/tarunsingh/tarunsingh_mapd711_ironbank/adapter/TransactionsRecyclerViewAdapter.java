package com.example.tarunsingh.tarunsingh_mapd711_ironbank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity.TransactionDetailsActivity;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.fragment.ClientsListFragment;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Transaction;

import java.util.List;

/**
 * Created by tarunsingh on 2018-01-08.
 */

public class TransactionsRecyclerViewAdapter extends RecyclerView.Adapter<TransactionsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private final List<Transaction> transactions;

    public TransactionsRecyclerViewAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public TransactionsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_list_item, parent, false);
        return new TransactionsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransactionsRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.transaction = transactions.get(position);
        holder.tvTransactionName.setText(holder.transaction.getTransactionName());
        holder.tvTransactionDate.setText(holder.transaction.getTransactionDate());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Transaction details screen.
                Intent intent = new Intent(context, TransactionDetailsActivity.class);
                intent.putExtra(TransactionDetailsActivity.KEY_TRANSACTION, holder.transaction);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTransactionName;
        public final TextView tvTransactionDate;
        public Transaction transaction;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTransactionName = view.findViewById(R.id.tv_transaction_name);
            tvTransactionDate = view.findViewById(R.id.tv_transaction_date);
        }
    }
}
