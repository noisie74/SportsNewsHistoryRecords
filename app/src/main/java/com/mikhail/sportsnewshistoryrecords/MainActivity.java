package com.mikhail.sportsnewshistoryrecords;

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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.mikhail.sportsnewshistoryrecords.adapters.HistoryAdapter;
import com.mikhail.sportsnewshistoryrecords.adapters.ViewPagerAdapter;
import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.HistoryFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.NewsDetailsFragment;

import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytAllSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytBaseballSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytBasketballSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytFootballSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytHockeySportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment.nytSoccerSportsNews;
import static com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment.bundesligaSearch;
import static com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment.footballSearch;
import static com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment.mlsSearch;
import static com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment.nbaSearch;
import static com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment.serieASearch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout fragContainer;
    private AllSportsFragment allSportsFragment;
    Toolbar toolbar;
    private static final String[] paths = {"Top News", "Football", "Basketball", "Baseball", "Hockey", "Soccer"};
    ArrayAdapter<String> adapter;
    TabLayout tabLayout;
    NewsDetailsFragment newsDetailsFragment;
    PopupMenu popup;
    Spinner spinner;
    RelativeLayout root_layout;
//    String[] arraySpinner;
    HistoryAdapter historyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sports News");

//          spinner=new Spinner(this);
//         root_layout=(RelativeLayout)findViewById(R.id.relative_layout);


//        this.arraySpinner = new String[] {
//                "1", "2", "3", "4", "5"
//        };
        Spinner s = (Spinner)toolbar.findViewById(R.id.app_bar_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, paths);
        s.setAdapter(adapter);

        setViews();
        setFragment();

        historyAdapter = new HistoryAdapter();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Articles"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Records"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if (tabLayout != null){
            tabLayout.setVisibility(View.GONE);
        }

//        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(viewPagerAdapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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
        allSportsFragment = new AllSportsFragment();
        toolbar.setTitle("Sports News");
        newsDetailsFragment = new NewsDetailsFragment();
//        spinner = (Spinner) findViewById(R.id.spinner);


    }

    private void setFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        nytAllSportsNews();
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

//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//
//            root_layout.addView(spinner);
//            spinner.setAdapter(new ArrayAdapter<>(this,
//                    android.R.layout.simple_spinner_dropdown_item,paths));
//
//
////            popup();
//        }

//            if(allSportsFragment != null){
//               allSportsFragment.popup();
//            }
//            adapter = new ArrayAdapter<>(MainActivity.this,
//                    R.layout.support_simple_spinner_dropdown_item, paths);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//            spinner.setVisibility(View.VISIBLE);
//        }

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
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
//                tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
                break;
            case R.id.nfl:
            footballSearch();
//                topicFrag.setSections(BREAKING_NEWS);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Football News");
            toolbar.getChildAt(2).setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
            break;
            case R.id.nba:
                nbaSearch();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("NBA News");
                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
                break;
            case R.id.mlb:
                nytBaseballSportsNews();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, topicFrag);
                fragmentTransaction.commit();
                toolbar.setTitle("Baseball News");
                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
                break;
            case R.id.nhl:
                nytHockeySportsNews();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, topicFrag);
                fragmentTransaction.commit();
                toolbar.setTitle("NHL News");
                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
                break;
            case R.id.mls:
                mlsSearch();
//                topicFrag.setSections(BREAKING_NEWS);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("MLS News");
                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
//                tabLayout.removeAllTabs();
                break;
            case R.id.english_soccer:
                nytSoccerSportsNews();
//                topicFrag.setSections(WORLD);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, topicFrag);
                fragmentTransaction.commit();
                if (tabLayout != null){
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.spanish_soccer:
                HistoryFragment historyFragment = new HistoryFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, historyFragment);
                fragmentTransaction.commit();
                if (tabLayout != null){
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.italian_soccer:
                serieASearch();
//                topicFrag.setSections(WORLD);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("Italian Serie A News");
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null){
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;
            case R.id.german_soccer:
                bundesligaSearch();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("German Bundesliga News");
                toolbar.getChildAt(1).setVisibility(View.GONE);
                if (tabLayout != null){
                    tabLayout.setVisibility(View.VISIBLE);
                }
//                toolbar.setTitle(getString(R.string.world));
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void popup() {
         popup = new PopupMenu(MainActivity.this, fragContainer, Gravity.RIGHT);
        popup.getMenuInflater().inflate(R.menu.pop_up_menu, popup.getMenu());
        popup.show();
    }


}



