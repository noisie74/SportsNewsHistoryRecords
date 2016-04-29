package com.mikhail.sportsnewshistoryrecords;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.rx.NytAllSportsRxActivity;

import timber.log.Timber;

import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytAllSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytSoccerSportsNews;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout fragContainer;
    private AllSportsFragment articleListFragment;
    Toolbar toolbar;
    Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setViews();
        setFragment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setViews() {
        fragContainer = (FrameLayout) findViewById(R.id.frag_container);
        fragmentManager = getSupportFragmentManager();
        articleListFragment = new AllSportsFragment();
//        spinner = (Spinner) findViewById(R.id.spinner);


    }

    private void setFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        nytAllSportsNews();
        fragmentTransaction.add(R.id.frag_container, articleListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            popup();
//            adapter = new ArrayAdapter<>(MainActivity.this,
//                    R.layout.support_simple_spinner_dropdown_item, paths);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//            spinner.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        AllSportsFragment topicFrag = new AllSportsFragment();
        LeaguesFragment leaguesFragment = new LeaguesFragment();
        int id = item.getItemId();

        switch (id) {
            case R.id.top_news:
                nytAllSportsNews();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, topicFrag);
                fragmentTransaction.commit();
                toolbar.setTitle("Sports News");
                break;
            case R.id.english_soccer:
                nytSoccerSportsNews();
//                topicFrag.setSections(WORLD);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, topicFrag);
                fragmentTransaction.commit();
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.italian_soccer:
                leaguesFragment.serieASearch();
//                topicFrag.setSections(WORLD);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
                fragmentTransaction.commit();
//                toolbar.setTitle(getString(R.string.world));
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void popup() {
        PopupMenu popup = new PopupMenu(MainActivity.this,fragContainer); //the v is the view that you click replace it with your menuitem like : menu.getItem(1)
        popup.getMenuInflater().inflate(R.menu.pop_up_menu,popup.getMenu());
        popup.show();


    }

}



