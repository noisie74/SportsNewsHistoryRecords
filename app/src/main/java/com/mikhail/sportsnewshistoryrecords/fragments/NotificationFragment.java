package com.mikhail.sportsnewshistoryrecords.fragments;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.activities.MainActivity;
import com.mikhail.sportsnewshistoryrecords.interfaces.ControlToolbar;
import com.mikhail.sportsnewshistoryrecords.service.JobSchedulerService;

/**
 * Created by Mikhail on 5/10/16.
 */
public class NotificationFragment extends Fragment {

    public View v;
    public static final String BOOLEAN_CODE = "booleanCode";
    private String TOP_NEWS_CODE = "Pro football,Pro basketball,baseball,soccer,Hockey";
    private String FOOTBALL_CODE = "Pro%20Football";
    private String BASKETBALL_CODE = "Pro basketball";
    private String BASEBALL_CODE = "baseball";
    private String HOCKEY_CODE = "Hockey";
    private String SOCCER_CODE = "Soccer";
    private CheckBox topNews, football, basketball, baseball, hockey, soccer;
    private ControlToolbar controlToolbar;

    private boolean topNewsCheck = false;
    private boolean footballCheck = false;
    private boolean basketballCheck = false;
    private boolean baseballCheck = false;
    private boolean hockeyCheck = false;
    private boolean soccerCheck = false;
    public SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notifications_layout, container, false);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        setViews();
        setCheckboxClicks();
        hasOptionsMenu();

        return v;



    }

    private void setViews(){

        topNews = (CheckBox) v.findViewById(R.id.checkbox_top_news);
        football = (CheckBox) v.findViewById(R.id.checkbox_football);
        basketball = (CheckBox) v.findViewById(R.id.checkbox_basketball);
        baseball = (CheckBox) v.findViewById(R.id.checkbox_baseball);
        hockey = (CheckBox) v.findViewById(R.id.checkbox_hockey);
        soccer = (CheckBox) v.findViewById(R.id.checkbox_soccer);

        controlToolbar.showSpinner(false);
        controlToolbar.setTitle("Notifications");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controlToolbar = (ControlToolbar) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException();
        }

    }

    /**
     * method for checking if checkboxes are checked and making notifications for
     * the sections if they are checked
     * @param view
     */
    private void onCheckboxClicked(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) view).isChecked();
                switch (view.getId()) {
                    case R.id.checkbox_top_news:
                        if (checked) {
                            topNewsCheck = true;
                        } else {
                            topNewsCheck = false;
                        }
                        break;
                    case R.id.checkbox_football:
                        if (checked) {
                            footballCheck = true;
                        } else {
                            footballCheck = false;
                        }
                        break;
                    case R.id.checkbox_basketball:
                        if (checked) {
                            basketballCheck = true;
                        } else {
                            basketballCheck = false;
                        }
                        break;
                    case R.id.checkbox_baseball:
                        if (checked) {
                            baseballCheck = true;
                        } else {
                            baseballCheck = false;
                        }
                        break;
                    case R.id.checkbox_hockey:
                        if (checked) {
                            hockeyCheck = true;
                        } else {
                            hockeyCheck = false;
                        }
                        break;
                    case R.id.checkbox_soccer:
                        if (checked) {
                            soccerCheck = true;
                        } else {
                            soccerCheck = false;
                        }
                        break;
                    default:
                }
            }
        });
    }

    /**
     * Adds each checkbox to see if they have been checked
     */
    private void setCheckboxClicks() {
        onCheckboxClicked(topNews);
        onCheckboxClicked(football);
        onCheckboxClicked(basketball);
        onCheckboxClicked(baseball);
        onCheckboxClicked(hockey);
        onCheckboxClicked(soccer);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCheckBoxes();
    }

    @Override
    public void onResume() {
        super.onResume();
        getSavedCheckBoxes();
    }

    /**
     * Saves the state of the checkboxes in the shared preferences
     */



    private void saveCheckBoxes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(TOP_NEWS_CODE, topNewsCheck);
        editor.putBoolean(FOOTBALL_CODE, footballCheck);
        editor.putBoolean(BASKETBALL_CODE, basketballCheck);
        editor.putBoolean(BASEBALL_CODE, baseballCheck);
        editor.putBoolean(HOCKEY_CODE, hockeyCheck);
        editor.putBoolean(SOCCER_CODE, soccerCheck);
        editor.commit();
    }

    /**
     * Retrieves the state of the checkboxes from the shared preferences
     */

    private void getSavedCheckBoxes() {
        topNewsCheck = sharedPreferences.getBoolean(TOP_NEWS_CODE, topNewsCheck);
        topNews.setChecked(topNewsCheck);
        footballCheck = sharedPreferences.getBoolean(FOOTBALL_CODE, footballCheck);
        football.setChecked(footballCheck);
        basketballCheck = sharedPreferences.getBoolean(BASKETBALL_CODE, basketballCheck);
        basketball.setChecked(basketballCheck);
        baseballCheck = sharedPreferences.getBoolean(BASEBALL_CODE, baseballCheck);
        baseball.setChecked(baseballCheck);
        hockeyCheck = sharedPreferences.getBoolean(HOCKEY_CODE, hockeyCheck);
        hockey.setChecked(hockeyCheck);
        soccerCheck = sharedPreferences.getBoolean(SOCCER_CODE, soccerCheck);
        soccer.setChecked(soccerCheck);
    }

    /**
     * Builds the JobScheduler in this activity.
     * Sets the time for it to call the api every 300 mins
     * Sends an array of booleans to the service to let the
     * service know which sections it needs to create notifications for
     */
    @TargetApi(21)
    public void setJobHandler() {
        if (Integer.valueOf(Build.VERSION.SDK_INT) > 20) {
            PersistableBundle bundle = new PersistableBundle();

            boolean[] booleanArray = new boolean[6];
            booleanArray[0] = topNewsCheck;
            booleanArray[1] = footballCheck;
            booleanArray[2] = basketballCheck;
            booleanArray[3] = baseballCheck;
            booleanArray[4] = hockeyCheck;
            booleanArray[5] = soccerCheck;
            bundle.putBooleanArray(BOOLEAN_CODE, booleanArray);

            if (getActivity() != null){
                JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getActivity(), JobSchedulerService.class));
                builder.setPeriodic(18000000).setExtras(bundle);

                JobScheduler mJobScheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                if (mJobScheduler.schedule(builder.build()) <= 0) {
                    //If something goes wrong
            }

            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
