package com.example.facultyprofile.Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Publications implements Serializable {

    private String abstracts;
    private String author;
    private String publisher;
    private String title;
    private String url;
    private String year;
    private int citedby;


    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCitedby(int citedby) { this.citedby = citedby; }

    public int getCitedby() { return citedby; }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("abstracts",abstracts);
        result.put("author",author);
        result.put("publisher",publisher);
        result.put("title",title);
        result.put("url",url);
        result.put("year",year);
        result.put("citedby",citedby);
        return result;
    }
}

