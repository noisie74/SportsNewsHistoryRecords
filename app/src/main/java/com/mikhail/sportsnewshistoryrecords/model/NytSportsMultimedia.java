package com.mikhail.sportsnewshistoryrecords.model;

/**
 * Created by Mikhail on 5/1/16.
 */
public class NytSportsMultimedia {

    private String format;
    private int height;
    private int weight;
    private String url;

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
