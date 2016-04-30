package com.mikhail.sportsnewshistoryrecords.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.mikhail.sportsnewshistoryrecords.fragments.LeaguesFragment;
import com.mikhail.sportsnewshistoryrecords.fragments.NewsDetailsFragment;

/**
 * Created by Mikhail on 4/29/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LeaguesFragment tab1 = new LeaguesFragment();
                Log.d("MainActivity", "in Tab 1");
                return tab1;
            case 1:
                NewsDetailsFragment tab2 = new NewsDetailsFragment();
                Log.d("MainActivity", "in Tab 2");
                return tab2;
//            case 2:
//                ContactsFragment tab3 = new ContactsFragment();
//                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
