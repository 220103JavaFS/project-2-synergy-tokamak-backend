package com.app.satpoint.models;

public class FavDTO {
    private int userId;
    private int noradId;
    private boolean isFav;

    public FavDTO() {
    }

    public FavDTO(int userId, int noradId, boolean isFav) {
        this.userId = userId;
        this.noradId = noradId;
        this.isFav = isFav;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoradId() {
        return noradId;
    }

    public void setNoradId(int noradId) {
        this.noradId = noradId;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public String toString() {
        return "FavDTO{" +
                "userId=" + userId +
                ", noradId=" + noradId +
                ", isFav=" + isFav +
                '}';
    }
}
