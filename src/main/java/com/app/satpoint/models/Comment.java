package com.app.satpoint.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long commentId;

    @Column(nullable = false, unique = true)
    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="satId", nullable = false)
    private Satellite satellite; //might use a URL too?

    private int numFavorites;

    @Column(nullable = false, unique = true)
    private String comment;

    private int likes;
    private int dislikes;

    public Comment() {
    }

    @Autowired
    public Comment(long commentId, int userId, Satellite satellite, int numFavorites, String comment, int likes, int dislikes) {
        this.commentId = commentId;
        this.userId = userId;
        this.satellite = satellite;
        this.numFavorites = numFavorites;
        this.comment = comment;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentID) {
        this.commentId = commentID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public void setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentId +
                ", userID=" + userId +
                ", satellite=" + satellite +
                ", numFavorites=" + numFavorites +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
