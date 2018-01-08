package com.example.tarunsingh.tarunsingh_mapd711_ironbank.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity.AddClientActivity;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.adapter.ClientsRecyclerViewAdapter;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.miscelleneous.Utility;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.RetrofitInstance;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.network.TellerResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ClientsListFragment extends Fragment {

    private static final String ARG_TELLER_ID = "tellerId";
    private OnListFragmentInteractionListener mListener;
    private String tellerId;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClientsListFragment() {
    }

    public static ClientsListFragment newInstance(String tellerId) {
        ClientsListFragment fragment = new ClientsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TELLER_ID, tellerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tellerId = getArguments().getString(ARG_TELLER_ID);
            if (!TextUtils.isEmpty(tellerId)) {
                fetchClientsList();
            } else {
                Toast.makeText(getActivity(), R.string.invalid_teller_id, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), R.string.invalid_teller_id, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);

        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        }

        FloatingActionButton fab = view.findViewById(R.id.fab_add_client);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClientActivity.class);
                intent.putExtra(AddClientActivity.KEY_TELLER_ID, tellerId);
                startActivity(intent);
            }
        });

        return view;
    }

    private void fetchClientsList() {

        Utility.showProgress(true, getActivity());

        //Create handle for the RetrofitInstance interface
        TellerResource service = RetrofitInstance.getRetrofitInstance().create(TellerResource.class);
        // Make a rest call to fetch all associated clients with currently logged in teller.
        Call<List<Client>> call = service.getClientsByTellerId(tellerId);

        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                recyclerView.setAdapter(new ClientsRecyclerViewAdapter(getActivity(), clients, mListener));
                Utility.showProgress(false, getActivity());
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Utility.showProgress(false, getActivity());
                Toast.makeText(getActivity(), "Login failed with code : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Client client);
    }
}
