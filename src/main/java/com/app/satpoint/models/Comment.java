package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="satId", nullable = false)
    @JsonBackReference
    private Satellite satellite;

    @Transient
    private int satNoradId;

    @Column(nullable = false, unique = false)
    private String comment;

    private String date;


    public Comment() {
    }

    public Comment(long commentId, User user, Satellite satellite, String comment, String date) {
        this.commentId = commentId;
        this.user = user;
        this.satellite = satellite;
        this.comment = comment;
        this.date = date;
    }

    public Comment(long commentId, User user, Satellite satellite, int satNoradId, String comment, String date) {
        this.commentId = commentId;
        this.user = user;
        this.satellite = satellite;
        this.satNoradId = satNoradId;
        this.comment = comment;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSatNoradId() {
        return satNoradId;
    }

    public void setSatNoradId(int satNoradId) {
        this.satNoradId = satNoradId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", user=" + user +
                ", satellite=" + satellite +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
