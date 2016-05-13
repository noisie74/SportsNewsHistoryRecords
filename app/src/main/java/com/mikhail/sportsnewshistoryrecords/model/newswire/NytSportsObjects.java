package com.mikhail.sportsnewshistoryrecords.model.newswire;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Array of objects from NYT News Wire API
 */
public class NytSportsObjects {

    private String section;
    private String subsection;
    private String title;
    private String url;
    private String thumbnail_standard;
    @SerializedName("abstract") private String abstractResult;
    private String created_date;
    @JsonAdapter(MultimediaAdapter.class)
    private NytSportsMultimedia[] multimedia;

    public NytSportsMultimedia[] getMultimedia() {
        return multimedia;
    }


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }

    public void setThumbnail_standard(String thumbnail_standard) {
        this.thumbnail_standard = thumbnail_standard;
    }

    public String getAbstractResult() {
        return abstractResult;
    }

    public void setAbstractResult(String abstractResult) {
        this.abstractResult = abstractResult;
    }


    public long getCreated_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");

        long timeInMillis = 0;
        try {
            Date mDate = sdf.parse(created_date);
            timeInMillis = mDate.getTime();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return timeInMillis;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }


}
