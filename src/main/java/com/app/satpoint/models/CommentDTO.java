package com.app.satpoint.models;

public class CommentDTO {

    private int userId;
    private int noradId;
    private String message;
    private String date;

    public CommentDTO() {
    }

    public CommentDTO(int userId, int noradId, String message, String date) {
        this.userId = userId;
        this.noradId = noradId;
        this.message = message;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

