package com.mikhail.sportsnewshistoryrecords.model.search;

/**
 * Array of  Multimedia objects from NYT News Wire API
 */
public class Multimedia {

    String url;

    public String getUrl() {
        return  "http://nytimes.com/" + url;
    }

}
