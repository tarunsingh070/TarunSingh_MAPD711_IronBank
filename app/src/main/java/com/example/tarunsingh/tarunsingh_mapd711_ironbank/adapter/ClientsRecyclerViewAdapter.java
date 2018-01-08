package com.example.tarunsingh.tarunsingh_mapd711_ironbank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.fragment.ClientsListFragment.OnListFragmentInteractionListener;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;

import java.util.List;

public class ClientsRecyclerViewAdapter extends RecyclerView.Adapter<ClientsRecyclerViewAdapter.ViewHolder> {

    private final List<Client> clients;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public ClientsRecyclerViewAdapter(Context context, List<Client> clients, OnListFragmentInteractionListener listener) {
        this.context = context;
        this.clients = clients;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_client_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.client = clients.get(position);
        holder.tvFullName.setText(context.getString(R.string.full_name, holder.client.getFirstName()
                , holder.client.getLastName()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.client);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvFullName;
        public Client client;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvFullName = view.findViewById(R.id.tv_fullname);
        }
    }
}
