package com.mikhail.sportsnewshistoryrecords.interfaces;

/**
 * Connection between all fragments
 *and activities. Manages toolbar
 */
public interface ControlToolbar {

    void showSpinner(boolean visible);
    void showTitle(boolean visible);
    void setTitle(String title);
}
