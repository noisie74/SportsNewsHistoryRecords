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
import android.view.View;

import com.mikhail.sportsnewshistoryrecords.adapters.ViewPagerAdapter;
import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;

public class LeaguesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    AllSportsFragment allSportsFragment;
    LeaguesFragment leaguesFragment;
    private int mNavigationItemId;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int key = getIntent().getIntExtra("KEY", 0);
        setContentView(R.layout.activity_leagues);
        toolbar = (Toolbar) findViewById(R.id.toolbar_leagues);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_leagues);
        tabLayout.addTab(tabLayout.newTab().setText("Articles"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Records"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        fragmentManager = getSupportFragmentManager();
        allSportsFragment = new AllSportsFragment();
        leaguesFragment = new LeaguesFragment();

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_leagues);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_leagues);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
        allSportsFragment = new AllSportsFragment();
        leaguesFragment = new LeaguesFragment();

        mNavigationItemId = item.getItemId();
        leaguesFragment.setFragmentType(mNavigationItemId);

        switch (mNavigationItemId) {
            case R.id.top_news:

                if (viewPager != null) {
                    viewPager.setVisibility(View.GONE);
                }

                if (tabLayout != null) {
                    tabLayout.setVisibility(View.GONE);
                }
//                if (spinner != null) {
//                    spinner.setVisibility(View.VISIBLE);
//                }

                allSportsFragment.nytAllSportsNews();
//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("Sports News");
                toolbar.getChildAt(1).setVisibility(View.VISIBLE);

//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);

//                if (fragContainer != null){
//                    fragContainer.setVisibility(View.VISIBLE);
//                }


                if (tabLayout != null) {
                    tabLayout.setVisibility(View.GONE);

                }
//                tabLayout.removeAllTabs();
                break;
            case R.id.nfl:


                if (viewPager != null) {
                    viewPager.setVisibility(View.VISIBLE);
                }


//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NFL Football");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);


//                tabLayout.setVisibility(View.GONE);
                break;
            case R.id.nba:

                viewPager.setVisibility(View.VISIBLE);
                viewPagerAdapter.setFragmentType(mNavigationItemId);


                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NBA Basketball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);

                break;
            case R.id.mlb:

                viewPager.setVisibility(View.VISIBLE);
                viewPagerAdapter.setFragmentType(mNavigationItemId);


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("MLB Baseball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.nhl:

                viewPager.setVisibility(View.VISIBLE);


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NHL Hockey");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                tabLayout.removeAllTabs();
                break;
            case R.id.mls:

                viewPager.setVisibility(View.VISIBLE);

//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("MLS Soccer");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.english_soccer:


                viewPager.setVisibility(View.VISIBLE);

//                topicFrag.setSections(WORLD);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("English Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.spanish_soccer:

                viewPager.setVisibility(View.VISIBLE);


//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("Spanish Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.italian_soccer:

                viewPager.setVisibility(View.VISIBLE);


//                topicFrag.setSections(WORLD);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("Italian Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null) {
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.german_soccer:

                viewPager.setVisibility(View.VISIBLE);


//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("German Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
//                toolbar.setTitle(getString(R.string.world));
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
