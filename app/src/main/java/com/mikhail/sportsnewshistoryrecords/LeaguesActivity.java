package com.mikhail.sportsnewshistoryrecords;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mikhail.sportsnewshistoryrecords.adapters.ViewPagerAdapter;

public class LeaguesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private int mNavigationItemId;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    Intent intent;
    public static final String RETURN_TO_MAIN_ACTIVITY = "backToMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int key = getIntent().getIntExtra("KEY", 0);

        setContentView(R.layout.activity_leagues);
        toolbar = (Toolbar) findViewById(R.id.toolbar_leagues);
        setSupportActionBar(toolbar);

        intent = new Intent(LeaguesActivity.this, MainActivity.class);


        tabLayout = (TabLayout) findViewById(R.id.tab_layout_leagues);
        tabLayout.addTab(tabLayout.newTab().setText("Articles"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Records"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager) findViewById(R.id.viewPager_leagues);
        viewPagerAdapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPagerAdapter.setFragmentType(key);

        Log.d("MainActivity", "Tab!" + tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
//        viewPager.setVisibility(View.GONE);
//        fragContainer.setVisibility(View.GONE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_leagues);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view_leagues);
        navigationView.setNavigationItemSelectedListener(this);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                int id = tab.getPosition();

//                if (id == 0) {
//                    viewPagerAdapter.setFragmentType(mNavigationItemId);
//
//                }

//                switch (id) {
//                    case 0:
//                        viewPagerAdapter.setFragmentType(mNavigationItemId);
//                        break;
//
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_leagues);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
//            intent.putExtra(RETURN_TO_MAIN_ACTIVITY, R.id.top_news);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int mNavigationItemId = item.getItemId();
//
//        if (mNavigationItemId == R.mNavigationItemId.action_settings) {
//
//            root_layout.addView(spinner);
//            spinner.setAdapter(new ArrayAdapter<>(this,
//                    android.R.layout.simple_spinner_dropdown_item,paths));
//
//
////            popup();
//        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        mNavigationItemId = item.getItemId();
        viewPagerAdapter.setFragmentType(mNavigationItemId);

        switch (mNavigationItemId) {
            case R.id.top_news:
                // TODO send user back to main activty
                intent.putExtra(RETURN_TO_MAIN_ACTIVITY, R.id.top_news);
                startActivity(intent);

                break;
            case R.id.nfl:
                toolbar.setTitle("NFL Football");
                viewPagerAdapter.refreshData();
                break;
            case R.id.nba:
                toolbar.setTitle("NBA Basketball");
                viewPagerAdapter.refreshData();
                break;
            case R.id.mlb:
                toolbar.setTitle("MLB Baseball");
                viewPagerAdapter.refreshData();
                break;
            case R.id.nhl:
                toolbar.setTitle("NHL Hockey");
                viewPagerAdapter.refreshData();
                break;
            case R.id.mls:
                toolbar.setTitle("MLS Soccer");
                viewPagerAdapter.refreshData();
                break;
            case R.id.english_soccer:
                toolbar.setTitle("English Soccer");
                viewPagerAdapter.refreshData();
                break;
            case R.id.spanish_soccer:
                toolbar.setTitle("Spanish Soccer");
                viewPagerAdapter.refreshData();
                break;
            case R.id.italian_soccer:
                toolbar.setTitle("Italian Soccer");
                viewPagerAdapter.refreshData();
                break;
            case R.id.german_soccer:
                toolbar.setTitle("German Soccer");
                viewPagerAdapter.refreshData();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_leagues);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }


}
