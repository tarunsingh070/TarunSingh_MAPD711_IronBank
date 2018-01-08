package com.example.tarunsingh.tarunsingh_mapd711_ironbank.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.fragment.ClientsListFragment;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.fragment.TerminationRequestsListFragment;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Client;
import com.example.tarunsingh.tarunsingh_mapd711_ironbank.model.Teller;

public class TellerHomeActivity extends AppCompatActivity
    implements ClientsListFragment.OnListFragmentInteractionListener,
        TerminationRequestsListFragment.OnListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    private Teller teller;
    private TextView tvFullName;
    private TextView tvUserName;
    private ImageView ivProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teller_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        teller = getIntent().getParcelableExtra(LoginActivity.KEY_TELLER);



//        ivProfile = findViewById(R.id.iv_profile);
//        ivProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Todo: Open teller profile activity.
//            }
//        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_clients);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        View headerView = navigationView.getHeaderView(0);
        tvUserName = headerView.findViewById(R.id.tv_username);
        tvFullName = headerView.findViewById(R.id.tv_fullname);

        tvUserName.setText(getString(R.string.welcome, teller.getUserName()));
        tvFullName.setText(getString(R.string.full_name, teller.getFirstName(), teller.getLastName()));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teller_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_clients) {
            fragment = ClientsListFragment.newInstance(teller.getId());
        } else if (id == R.id.nav_terminate_requests) {
            fragment = TerminationRequestsListFragment.newInstance(teller.getId());
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Client client) {
//        Fragment fragment = ClientDetailsFragment.newInstance(teller.getId(), client.getId());
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.content_frame, fragment);
//        ft.commit();
        Intent intent = new Intent(this, ClientDetailsActivity.class);
        intent.putExtra(ClientDetailsActivity.KEY_TELLER_ID, teller.getId());
        intent.putExtra(ClientDetailsActivity.KEY_CLIENT_ID, client.getId());
        startActivity(intent);
    }

}
