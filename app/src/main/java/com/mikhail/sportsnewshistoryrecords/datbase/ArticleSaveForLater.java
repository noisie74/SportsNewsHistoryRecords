package com.mikhail.sportsnewshistoryrecords.datbase;

/**
 * Created by Mikhail on 5/6/16.
 */
public class ArticleSaveForLater {

    private int id;
    private String html;
    private String title;
    private String snippet;
    private String url;
    private String image;
    private long code;

    public ArticleSaveForLater() {
    }

    public ArticleSaveForLater(String html, String title, String snippet, String url, String image) {
        this.html = html;
        this.title = title;
        this.snippet = snippet;
        this.url = url;
        this.image = image;
        this.code = (System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public static String titleForToast(String title){
        int titleLength = title.length();
        int limit = 30;
        if(titleLength > limit) {
            titleLength = limit;
        }
        return title.substring(0,titleLength) + "...";
    }
}
