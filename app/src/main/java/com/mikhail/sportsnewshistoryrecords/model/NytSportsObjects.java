package com.mikhail.sportsnewshistoryrecords.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mikhail on 4/27/16.
 */
public class NytSportsObjects {

    private String section;
    private String subsection;
    private String title;
    private String url;
    private String thumbnail_standard;
    @SerializedName("abstract") private String abstractResult;
    private String created_date;
}
