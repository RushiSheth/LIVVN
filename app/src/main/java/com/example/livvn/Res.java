package com.example.livvn;

import java.io.Serializable;

public class Res implements Serializable {
    public String name;
    public String image;
    public String info;
    public String rating;

    public Res() {

    }

    public Res(String name, String image, String info, String rating) {
        this.name = name;
        this.image = image;
        this.info = info;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
