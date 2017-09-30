package com.hack.models;

import java.util.ArrayList;

/**
 * Created by sajidhz on 9/30/2017.
 */

public class AttractionsModel {
    private String name, thumbnailUrl, place, description;
    private double rating;

    public AttractionsModel() {
    }

    public AttractionsModel(String name, String thumbnailUrl, String place, double rating,
                            String description) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.place = place;
        this.rating = rating;
        this.description = description;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}