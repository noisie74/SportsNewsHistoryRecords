package com.mikhail.sportsnewshistoryrecords.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.mikhail.sportsnewshistoryrecords.fragments.HistoryFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.RecordsFragment;

/**
 * Created by Mikhail on 4/29/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int fragmentType;
    int mNumOfTabs;
    private LeaguesFragment leaguesFragment;
    private HistoryFragment historyFragment;
    private RecordsFragment recordsFragment;


    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }

    public void refreshData() {
        if (leaguesFragment != null) {
            leaguesFragment.setFragmentType(fragmentType);
        }
        if (historyFragment != null) {
            historyFragment.setFragmentType(fragmentType);
        }
        if (recordsFragment != null) {
            recordsFragment.setFragmentType(fragmentType);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                Log.d("ViewPager", "in Tab 1");
                if (leaguesFragment == null) {
                    leaguesFragment = new LeaguesFragment();
                }
                leaguesFragment.setFragmentType(fragmentType);
                fragment = leaguesFragment;
                break;
            case 1:
                Log.d("ViewPager", "in Tab 2");

                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                }
                historyFragment.setFragmentType(fragmentType);
                fragment = historyFragment;
                break;
            case 2:
                Log.d("ViewPager", "in Tab 3");
                if (recordsFragment == null) {
                    recordsFragment = new RecordsFragment();
                }
                recordsFragment.setFragmentType(fragmentType);
                fragment = recordsFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
