package com.example.facultyprofile.Models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Professors implements Serializable {

    private String name;
    private String url_picture;
    private int citedby;
    private int hindex;
    private int hindex5y;
    private int i10index;
    private int i10index5y;
    private ArrayList<String> interests;
    private ArrayList<Publications> publications;

    public String getname() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public void setUrl_picture(String url_picture) {
        this.url_picture = url_picture;
    }

    public void setCitedby(int citedby) { this.citedby = citedby; }

    public int getCitedby() { return citedby; }

    public void setHindex(int hindex) { this.hindex = hindex; }

    public int getHindex() { return hindex; }

    public void setHindex5y(int hindex5y) { this.hindex5y = hindex5y; }

    public int getHindex5y() { return hindex5y; }

    public void setI10index(int i10index) { this.i10index = i10index; }

    public int getI10index() { return i10index; }

    public void setI10index5y(int i10index5y) { this.i10index5y = i10index5y; }

    public int getI10index5y() { return i10index5y; }

    public void setInterests(ArrayList<String> interests) { this.interests = interests; }

    public ArrayList<String> getInterests() { return interests; }

    public void setPublications(ArrayList<Publications> publications) { this.publications = publications; }

    public ArrayList<Publications> getPublications() { return publications; }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name",name);
        result.put("url_picture",url_picture);
        result.put("citedby",citedby);
        result.put("hindex",hindex);
        result.put("hindex5y",hindex5y);
        result.put("i10index",i10index);
        result.put("i10index5y",i10index5y);
        result.put("interests",interests);
        result.put("publications",publications);
        return result;
    }
}

