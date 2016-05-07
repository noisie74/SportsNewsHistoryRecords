package com.mikhail.sportsnewshistoryrecords;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mikhail.sportsnewshistoryrecords.adapters.HistoryAdapter;
import com.mikhail.sportsnewshistoryrecords.adapters.ViewPagerAdapter;
import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.HistoryFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.NewsDetailsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.RecordsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewsDetailsFragment.ControlToolbar {

    protected RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout fragContainer;
    private AllSportsFragment allSportsFragment;
    private int mNavigationItemId;
    Toolbar toolbar;
    private static final String[] paths = {"Top News", "Football", "Basketball", "Baseball", "Hockey", "Soccer"};
    NewsDetailsFragment newsDetailsFragment;
    LeaguesFragment leaguesFragment;
    public static final String KEY = "KEY";
    Spinner spinner;
    String[] articleDetails;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sports News");


        spinner = (Spinner) toolbar.findViewById(R.id.app_bar_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, paths);
        spinner.setAdapter(adapter);

        setViews();
        setFragment();

        intent = new Intent(MainActivity.this, LeaguesActivity.class);
        int key = getIntent().getIntExtra("backToMainActivity", 0);
        allSportsFragment.setFragment(key);


//
//        if (articleDetails != null){
//            articleDetails = getIntent().getExtras().getStringArray("searchedArticle");
//        }
//        if (articleDetails != null) {
//            // TODO you have to load detail fragment because we come from leagues fragment
//            newsDetailsFragment = new NewsDetailsFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.frag_container, newsDetailsFragment);
//            fragmentTransaction.commit();
//        }




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int spinnerPosition = spinner.getSelectedItemPosition();

                switch (spinnerPosition) {
                    case 0:
                        allSportsFragment.nytAllSportsNews();
                        Toast.makeText(MainActivity.this, "Top News", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        allSportsFragment.nytFootballSportsNews();
                        Toast.makeText(MainActivity.this, "Football", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        allSportsFragment.nytBasketballSportsNews();
                        Toast.makeText(MainActivity.this, "Basketball", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        allSportsFragment.nytBaseballSportsNews();
                        Toast.makeText(MainActivity.this, "Baseball", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        allSportsFragment.nytHockeySportsNews();
                        Toast.makeText(MainActivity.this, "Hockey", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        allSportsFragment.nytSoccerSportsNews();
                        Toast.makeText(MainActivity.this, "Soccer", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        historyAdapter = new HistoryAdapter();
//
//        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Articles"));
//        tabLayout.addTab(tabLayout.newTab().setText("History"));
//        tabLayout.addTab(tabLayout.newTab().setText("Records"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        if (tabLayout != null) {
//            tabLayout.setVisibility(View.GONE);
//        }
//
//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPagerAdapter = new ViewPagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPagerAdapter.setFragmentType(R.id.nfl);
//        Log.d("MainActivity", "Tab!" + tabLayout.getTabCount());
//        viewPager.setAdapter(viewPagerAdapter);
//        viewPager.setVisibility(View.GONE);
////        fragContainer.setVisibility(View.GONE);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                viewPager.setCurrentItem(tab.getPosition());
//
//                int id = tab.getPosition();
//
//                switch (id) {
//                    case 0:
//                        leaguesFragment = new LeaguesFragment();
//                        leaguesFragment.setFragmentType(mNavigationItemId);
////                        nytSoccerSportsNews();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                        fragmentTransaction.commit();
//                        break;
//                    case 1:
//                        historyFragment = new HistoryFragment();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.frag_container, historyFragment);
//                        fragmentTransaction.commit();
//                        break;
//                    case 2:
//                        recordsFragment = new RecordsFragment();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.frag_container, recordsFragment);
//                        fragmentTransaction.commit();
//                        break;
//
//
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void showSpinner(boolean visible) {
        if (visible) {
            spinner.setVisibility(View.VISIBLE);
        } else {
            spinner.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTitle(boolean visible) {

        if (visible) {
            toolbar.setTitle("");
        } else {
            toolbar.setTitle("Sports News");
        }
    }

    private void setViews() {
        fragContainer = (FrameLayout) findViewById(R.id.frag_container);
        fragmentManager = getSupportFragmentManager();
        allSportsFragment = new AllSportsFragment();
        toolbar.setTitle("Sports News");
        newsDetailsFragment = new NewsDetailsFragment();
//        spinner = (Spinner) findViewById(R.mNavigationItemId.spinner);


    }

    private void setFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frag_container, allSportsFragment);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        allSportsFragment = new AllSportsFragment();
        leaguesFragment = new LeaguesFragment();

        mNavigationItemId = item.getItemId();
//        leaguesFragment.setFragmentType(mNavigationItemId);

        switch (mNavigationItemId) {
            case R.id.top_news:

//                if (viewPager != null) {
//                    viewPager.setVisibility(View.GONE);
//                }
//
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.GONE);
//                }
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.VISIBLE);
//                }
                if (spinner != null) {
                    spinner.setSelection(0);
                }

                allSportsFragment.nytAllSportsNews();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("Sports News");
                toolbar.getChildAt(1).setVisibility(View.VISIBLE);

//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);

//                if (fragContainer != null){
//                    fragContainer.setVisibility(View.VISIBLE);
//                }


//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.GONE);
//
//                }
//                tabLayout.removeAllTabs();
                break;
            case R.id.nfl:

                intent.putExtra(KEY, R.id.nfl);
                startActivity(intent);

//                if (viewPager != null) {
//                    viewPager.setVisibility(View.VISIBLE);
//                }
//
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }

//                topicFrag.setSections(BREAKING_NEWS);
                // TODO this is wrong
                // TODO You need to put the fragment into viewpager not switch it with cointainer..
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NFL Football");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);


//                tabLayout.setVisibility(View.GONE);
                break;
            case R.id.nba:

                intent.putExtra("KEY", R.id.nba);


                startActivity(intent);

//
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
//                if (spinner != null) {
//                    spinner.setVisibility(View.GONE);
//                }

//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NBA Basketball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);

                break;
            case R.id.mlb:


//                viewPager.setVisibility(View.VISIBLE);
//                viewPagerAdapter.setFragmentType(mNavigationItemId);
//
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }
                intent.putExtra("KEY", R.id.mlb);
                startActivity(intent);


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("MLB Baseball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
                break;
            case R.id.nhl:

//                viewPager.setVisibility(View.VISIBLE);

//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }
                intent.putExtra("KEY", R.id.nhl);
                startActivity(intent);


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("NHL Hockey");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
//                tabLayout.removeAllTabs();
                break;
            case R.id.mls:

//                viewPager.setVisibility(View.VISIBLE);
                intent.putExtra("KEY", R.id.mls);
                startActivity(intent);


//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("MLS Soccer");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                toolbar.getChildAt(1).setVisibility(View.GONE);

                break;
            case R.id.english_soccer:

                intent.putExtra("KEY", R.id.english_soccer);
                startActivity(intent);


//                viewPager.setVisibility(View.VISIBLE);

//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }

//                topicFrag.setSections(WORLD);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("English Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);

//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.spanish_soccer:

                intent.putExtra("KEY", R.id.spanish_soccer);
                startActivity(intent);


//                viewPager.setVisibility(View.VISIBLE);
//
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }

//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("Spanish Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);

//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.italian_soccer:

//                viewPager.setVisibility(View.VISIBLE);
                intent.putExtra("KEY", R.id.italian_soccer);
                startActivity(intent);

//
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }

//                topicFrag.setSections(WORLD);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
                toolbar.setTitle("Italian Soccer");
                toolbar.getChildAt(1).setVisibility(View.GONE);
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.german_soccer:

//                viewPager.setVisibility(View.VISIBLE);

                intent.putExtra("KEY", R.id.german_soccer);
                startActivity(intent);


//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }

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



