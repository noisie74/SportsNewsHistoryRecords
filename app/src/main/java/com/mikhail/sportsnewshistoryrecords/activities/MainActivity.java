package com.mikhail.sportsnewshistoryrecords.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.fragments.AboutFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.AllSportsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.details_fragment.NewsDetailsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.NotificationFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.details_fragment.SavedArticleDetailsFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.SavedArticleFragment;
import com.mikhail.sportsnewshistoryrecords.interfaces.ControlToolbar;
import com.mikhail.sportsnewshistoryrecords.interfaces.MainActivityControlAllSports;
import com.mikhail.sportsnewshistoryrecords.interfaces.SavedArticleControl;
import com.mikhail.sportsnewshistoryrecords.util.CheckNetworkConnection;

import static com.mikhail.sportsnewshistoryrecords.util.CheckNetworkConnection.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ControlToolbar, MainActivityControlAllSports,
        SavedArticleControl {

    public static final String KEY = "KEY";
    public static final String LEAGUES_TRANSITION = "backToMainActivity";
    public Spinner spinner;
    public int mNavigationItemId;
    public Context context;
    public LeaguesFragment leaguesFragment;
    private static final String[] spinnerItems = {"Top News", "Football",
            "Basketball", "Baseball", "Hockey", "Soccer"};
    private int key;
    private Toolbar toolbar;
    private Intent intent;
    private int spinnerPosition;
    private NewsDetailsFragment newsDetailsFragment;
    private FragmentManager fragmentManager;
    private AboutFragment aboutFragment;
    private NotificationFragment notificationFragment;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout fragContainer;
    private SavedArticleFragment savedArticleFragment;
    private SavedArticleDetailsFragment savedArticleDetailsFragment;
    private AllSportsFragment allSportsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        setIntent();
        setViews();
        setFragment();
        checkNetwork();
        setSpinnerClickListener();

    }


//    @Override
//    protected void onResume() {
////        setAllSportsFragment();
//        super.onResume();
//    }

    /**
     set click listener for spinner
     */
    private void setSpinnerClickListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = spinner.getSelectedItemPosition();
                Log.d("FromSecondActivity", "Spinner" + spinnerPosition);
                switch (spinnerPosition) {
                    case 0:
                        allSportsFragment.nytAllSportsNews();
                        break;
                    case 1:
                        allSportsFragment.nytFootballSportsNews();
                        break;
                    case 2:
                        allSportsFragment.nytBasketballSportsNews();
                        break;
                    case 3:
                        allSportsFragment.nytBaseballSportsNews();
                        break;
                    case 4:
                        allSportsFragment.nytHockeySportsNews();
                        break;
                    case 5:
                        allSportsFragment.nytSoccerSportsNews();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    /**
     * receive intent from leagues activity
     */
    private void setIntent() {
        key = getIntent().getIntExtra(LEAGUES_TRANSITION, R.id.top_news);
        intent = new Intent(MainActivity.this, LeaguesActivity.class);
    }


    /**
     * set bundle to display article
     * details fragment into webview
     */
    @Override
    public void setBundle(Bundle article) {
        newsDetailsFragment = new NewsDetailsFragment();
        newsDetailsFragment.setArguments(article);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, newsDetailsFragment);
        fragmentTransaction.commit();
    }

    /**
     * set bundle to display  saved article
     * details fragment into webview
     */
    @Override
    public void setSavedArticleBundle(Bundle article) {
        savedArticleDetailsFragment = new SavedArticleDetailsFragment();
        savedArticleDetailsFragment.setArguments(article);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, savedArticleDetailsFragment);
        fragmentTransaction.commit();
    }

    /**
     * set view for main activity
     */
    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_title_toolbar);
        spinner = (Spinner) toolbar.findViewById(R.id.app_bar_spinner);
        fragContainer = (FrameLayout) findViewById(R.id.frag_container);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, spinnerItems);
        spinner.setAdapter(adapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        allSportsFragment = new AllSportsFragment();
    }

    /**
     * set fragment according to intent
     * from leagues activity
     */
    private void setFragment() {
        if (key == R.id.top_news) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (allSportsFragment == null){
                allSportsFragment = new AllSportsFragment();
            }
            fragmentTransaction.add(R.id.frag_container, allSportsFragment);
            fragmentTransaction.commit();
            allSportsFragment.setFragment(key);
        } else if (key == R.id.favorites) {
            savedArticleFragment = new SavedArticleFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, savedArticleFragment);
            fragmentTransaction.commit();
            toolbar.setTitle(R.string.saved_stories_toolbar);
            if (spinner != null) {
                spinner.setVisibility(View.GONE);
            }
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        allSportsFragment = new AllSportsFragment();
        leaguesFragment = new LeaguesFragment();

        mNavigationItemId = item.getItemId();

        switch (mNavigationItemId) {
            case R.id.top_news:
                setSpinnerSelection();
                setAllSportsFragment();
                toolbar.getChildAt(1).setVisibility(View.VISIBLE);
                break;
            case R.id.nfl:
                startMyActivity(R.id.nfl);
                break;
            case R.id.nba:
                startMyActivity(R.id.nba);
                break;
            case R.id.mlb:
                startMyActivity(R.id.mlb);
                break;
            case R.id.nhl:
                startMyActivity(R.id.nhl);
                break;
            case R.id.mls:
                startMyActivity(R.id.mls);
                break;
            case R.id.english_soccer:
                startMyActivity(R.id.english_soccer);
                break;
            case R.id.spanish_soccer:
                startMyActivity(R.id.spanish_soccer);
                break;
            case R.id.italian_soccer:
                startMyActivity(R.id.italian_soccer);
                break;
            case R.id.german_soccer:
                startMyActivity(R.id.german_soccer);
                break;
            case R.id.favorites:
                savedArticleFragment = new SavedArticleFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, savedArticleFragment);
                fragmentTransaction.commit();
                toolbar.setTitle(R.string.saved_stories_title_favorites);
                if (spinner != null) {
                    spinner.setVisibility(View.GONE);
                }
                break;
            case R.id.notifications:
                notificationFragment = new NotificationFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, notificationFragment);
                fragmentTransaction.commit();
                break;
            case R.id.about:
                aboutFragment = new AboutFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, aboutFragment);
                fragmentTransaction.commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startMyActivity(int id) {
        intent.putExtra(KEY, id);
        startActivity(intent);
    }

    /**
     * check network connection
     */
    public void checkNetwork() {

        if (!isConnected(this.context)) {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                }
            }, 1600);

            Toast.makeText(MainActivity.this, R.string.no_network, Toast.LENGTH_LONG).show();
        }
    }

//    public static boolean isConnected(){
//
//        // return true when connected
//        // return false when not conencted
//        return false;
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
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
            toolbar.setTitle(R.string.app_title_toolbar);
        }
    }

    public void setSpinnerSelection(){
        if (spinner != null) {
            spinner.setSelection(0);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("MainActivity", "Back!");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (newsDetailsFragment != null) {
            Log.d("Main", "1st condition");
            fragContainer.setVisibility(View.VISIBLE);
            setAllSportsFragment();
            newsDetailsFragment = null;
            if (spinner != null) {
                spinner.setVisibility(View.VISIBLE);
            }
        } else if (savedArticleDetailsFragment != null) {
            savedArticleFragment = new SavedArticleFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, savedArticleFragment);
            fragmentTransaction.commit();
            savedArticleDetailsFragment = null;
        } else if (savedArticleFragment != null) {

            Log.d("Main", "2nd condition");
            fragContainer.setVisibility(View.VISIBLE);
            setAllSportsFragment();
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            setSpinnerSelection();
            savedArticleFragment = null;
        } else if (aboutFragment != null) {

            Log.d("Main", "3rd condition");
            fragContainer.setVisibility(View.VISIBLE);
            setAllSportsFragment();
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            setSpinnerSelection();
            aboutFragment = null;
        } else if (notificationFragment != null) {
            notificationFragment.setJobHandler();
            setAllSportsFragment();
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            setSpinnerSelection();
            notificationFragment = null;

        } else {
            Log.d("MainActivity", "No condition met");
            super.onBackPressed();
        }
    }

    /**
     set all sports fragment
     */
    private void setAllSportsFragment(){

        allSportsFragment = new AllSportsFragment();
        allSportsFragment.nytAllSportsNews();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
        fragmentTransaction.commit();
        toolbar.setTitle(R.string.app_title_toolbar);

    }

}



