package com.mikhail.sportsnewshistoryrecords.interfaces;

/**
 * Connection between Leagues Detail fragment
 * and activities. Manages viewPager and tabLayout
 */
public interface ControlLeaguesActivityLayout {
    void showViewPager(boolean visible);

    void showTabLayout(boolean visible);
}
