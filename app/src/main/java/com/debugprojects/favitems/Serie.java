package com.debugprojects.favitems;

public class Serie {
    private String name;
    private String description;
    private int rating;
    private int image_id;
    private boolean favorited;

    public Serie(String name, String description, int rating, int image_id, boolean favorited) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.image_id = image_id;
        this.favorited = favorited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
