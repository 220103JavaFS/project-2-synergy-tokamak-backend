package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="satId", nullable = false)
    @JsonBackReference
    private Satellite satellite;

    @Column(nullable = false, unique = true)
    private String comment;


    public Comment() {
    }

    public Comment(long commentId, User user, Satellite satellite, String comment) {
        this.commentId = commentId;
        this.user = user;
        this.satellite = satellite;
        this.comment = comment;
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


    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", user=" + user +
                ", satellite=" + satellite +
                ", comment='" + comment + '\'' +
                '}';
    }
}
