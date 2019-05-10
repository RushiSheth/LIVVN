package com.example.livvn;

public class Hos {
    public String name, image, info, rating, room;

    public Hos() {

    }

    public Hos(String name, String image, String info, String rating, String room) {
        this.name = name;
        this.image = image;
        this.info = info;
        this.room = room;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
