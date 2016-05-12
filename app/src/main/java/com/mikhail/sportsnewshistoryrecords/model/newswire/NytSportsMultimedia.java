package com.mikhail.sportsnewshistoryrecords.model.newswire;

/**
 * Array of  Multimedia objects from NYT News Wire API
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

    public void setUrl(String url) {
        this.url = url;
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
