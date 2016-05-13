package com.mikhail.sportsnewshistoryrecords.model.search;

/**
 * Object of NYT Article Search API
 * holds array of Doc objects
 */
public class Response {

    private Doc[] docs;

    public Doc[] getDocs() {
        return docs;
    }

}