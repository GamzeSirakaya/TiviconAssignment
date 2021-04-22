package com.example.tiviconassignment.model;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("image")
    public String image;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }


    public Movies(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

}

