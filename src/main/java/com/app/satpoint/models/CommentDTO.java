package com.app.satpoint.models;

public class CommentDTO {

    private int userId;
    private int noradId;
    private String message;

    public CommentDTO() {
    }

    public CommentDTO(int userId, int noradId, String message) {
        this.userId = userId;
        this.noradId = noradId;
        this.message = message;
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

