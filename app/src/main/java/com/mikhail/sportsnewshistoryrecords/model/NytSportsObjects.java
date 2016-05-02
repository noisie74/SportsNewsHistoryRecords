package com.mikhail.sportsnewshistoryrecords.model;

import com.google.gson.annotations.SerializedName;
import com.mikhail.sportsnewshistoryrecords.model.search.Multimedia;

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
//    private NytSportsMultimedia[] multimedia;
//
//    public NytSportsMultimedia[] getMultimedia() {
//        return multimedia;
//    }

    @Override
    public String toString() {
        return String.format("%s", title, abstractResult);
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
