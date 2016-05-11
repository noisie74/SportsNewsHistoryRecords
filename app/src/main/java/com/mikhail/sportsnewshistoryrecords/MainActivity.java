package com.mikhail.sportsnewshistoryrecords;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ControlToolbar, MainActivityControlAllSports,
        SavedArticleControl {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FrameLayout fragContainer;
    private AllSportsFragment allSportsFragment;
    private int mNavigationItemId;
    private Toolbar toolbar;
    private static final String[] paths = {"Top News", "Football", "Basketball", "Baseball", "Hockey", "Soccer"};
    NewsDetailsFragment newsDetailsFragment;
    LeaguesFragment leaguesFragment;
    public static final String KEY = "KEY";
    public static final String LEAGUES_TRANSITION = "backToMainActivity";
    Spinner spinner;
    Intent intent;
    SavedArticleFragment savedArticleFragment;
    private int key;
    SavedArticleDetailsFragment savedArticleDetailsFragment;
    int spinnerPosition;
    AboutFragment aboutFragment;
    NotificationFragment notificationFragment;


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

        key = getIntent().getIntExtra(LEAGUES_TRANSITION, R.id.top_news);
        setViews();
        setFragment();

        intent = new Intent(MainActivity.this, LeaguesActivity.class);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = spinner.getSelectedItemPosition();
                switch (spinnerPosition) {
                    case 0:
                        allSportsFragment.nytAllSportsNews();
//                        Toast.makeText(MainActivity.this, "Top News", Toast.LENGTH_SHORT).show();
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
            toolbar.setTitle("Sports News");
        }
    }

    @Override
    public void setBundle(Bundle article) {
        newsDetailsFragment = new NewsDetailsFragment();
        newsDetailsFragment.setArguments(article);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, newsDetailsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setSavedArticleBundle(Bundle article) {
        savedArticleDetailsFragment = new SavedArticleDetailsFragment();
        savedArticleDetailsFragment.setArguments(article);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, savedArticleDetailsFragment);
//        transaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setViews() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragContainer = (FrameLayout) findViewById(R.id.frag_container);
        fragmentManager = getSupportFragmentManager();
        allSportsFragment = new AllSportsFragment();
        toolbar.setTitle("Sports News");
//        newsDetailsFragment = new NewsDetailsFragment();
//        spinner = (Spinner) findViewById(R.mNavigationItemId.spinner);


    }

    private void setFragment() {

        if (key == R.id.top_news) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frag_container, allSportsFragment);
            fragmentTransaction.commit();
            allSportsFragment.setFragment(key);
        } else if (key == R.id.favorites) {
            savedArticleFragment = new SavedArticleFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, savedArticleFragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Saved stories");
            if (spinner != null) {
                spinner.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onBackPressed() {

        Log.d("MainActivity", "Back!");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (newsDetailsFragment != null) {
//            fragmentManager.popBackStack();
            Log.d("Main", "1st condition");
            fragContainer.setVisibility(View.VISIBLE);

            allSportsFragment = new AllSportsFragment();
            allSportsFragment.nytAllSportsNews();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Sports News");
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
            allSportsFragment = new AllSportsFragment();
            allSportsFragment.nytAllSportsNews();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Sports News");
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            if (spinner != null) {
                spinner.setSelection(0);
            }
            savedArticleFragment = null;
        } else if (aboutFragment != null) {

            Log.d("Main", "2nd condition");
                allSportsFragment.nytAllSportsNews();
            fragContainer.setVisibility(View.VISIBLE);
//            allSportsFragment = new AllSportsFragment();
            allSportsFragment.nytAllSportsNews();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
            fragmentTransaction.commit();
            toolbar.setTitle("Sports News");
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            if (spinner != null) {
                spinner.setSelection(0);
            }
            aboutFragment = null;
        } else if (notificationFragment != null) {
            notificationFragment.setJobHandler();

            allSportsFragment = new AllSportsFragment();
            allSportsFragment.nytAllSportsNews();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag_container, allSportsFragment);
            toolbar.setTitle("Sports News");
            toolbar.getChildAt(1).setVisibility(View.VISIBLE);
            if (spinner != null) {
                spinner.setSelection(0);
            }
            fragmentTransaction.commit();

            notificationFragment = null;

        } else {
            Log.d("MainActivity", "No condition met");
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
                break;
            case R.id.nba:

                intent.putExtra(KEY, R.id.nba);
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
//                toolbar.setTitle("NBA Basketball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
//                toolbar.getChildAt(1).setVisibility(View.GONE);

                break;
            case R.id.mlb:


//                viewPager.setVisibility(View.VISIBLE);
//                viewPagerAdapter.setFragmentType(mNavigationItemId);
//
//                if (fragContainer != null) {
//                    fragContainer.setVisibility(View.GONE);
//                }
                intent.putExtra(KEY, R.id.mlb);
                startActivity(intent);


//                topicFrag.setSections(BREAKING_NEWS);
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frag_container, leaguesFragment);
//                fragmentTransaction.commit();
//                toolbar.setTitle("MLB Baseball");
//                toolbar.getChildAt(2).setVisibility(View.VISIBLE);
//                toolbar.getChildAt(1).setVisibility(View.GONE);
//                if (tabLayout != null) {
//                    tabLayout.setVisibility(View.VISIBLE);
//                }
                break;
            case R.id.nhl:
                intent.putExtra(KEY, R.id.nhl);
                startActivity(intent);
                break;
            case R.id.mls:
                intent.putExtra(KEY, R.id.mls);
                startActivity(intent);
                break;
            case R.id.english_soccer:
                intent.putExtra(KEY, R.id.english_soccer);
                startActivity(intent);
                break;
            case R.id.spanish_soccer:
                intent.putExtra(KEY, R.id.spanish_soccer);
                startActivity(intent);
                break;
            case R.id.italian_soccer:
                intent.putExtra(KEY, R.id.italian_soccer);
                startActivity(intent);
                break;
            case R.id.german_soccer:
                intent.putExtra(KEY, R.id.german_soccer);
                startActivity(intent);
                break;
            case R.id.favorites:
                savedArticleFragment = new SavedArticleFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag_container, savedArticleFragment);
                fragmentTransaction.commit();
                toolbar.setTitle("Saved stories");
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
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

//    private void callJobScheduler() {
//
//        JobScheduler mJobScheduler;
//
//        mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(),
//                JobSchedulerService.class.getName()));
//        builder.setPeriodic(600000);
//
//        if (mJobScheduler.schedule(builder.build()) <= 0) {
//        }
//    }
}



