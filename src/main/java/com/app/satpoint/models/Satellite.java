package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "satellites")
public class Satellite {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long satId;

    @Column(nullable = false, unique = true)
    private String satName;
    private String satPicture; //might use a URL instead?
    private int numFavorites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "satellite")
    @JsonManagedReference
    private List<Comment> comments;

    public Satellite() {
    }

    @Autowired
    public Satellite(long satId, String satName, String satPicture, int numFavorites, List<Comment> comments) {
        this.satId = satId;
        this.satName = satName;
        this.satPicture = satPicture;
        this.numFavorites = numFavorites;
        this.comments = comments;
    }

    public long getSatId() {
        return satId;
    }

    public void setSatId(long satID) {
        this.satId = satID;
    }

    public String getSatName() {
        return satName;
    }

    public void setSatName(String satName) {
        this.satName = satName;
    }

    public String getSatPicture() {
        return satPicture;
    }

    public void setSatPicture(String satPicture) {
        this.satPicture = satPicture;
    }

    public int getNumFavorites() {
        return numFavorites;
    }

    public void setNumFavorites(int numFavorites) {
        this.numFavorites = numFavorites;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "satID=" + satId +
                ", satName='" + satName + '\'' +
                ", satPicture=" + satPicture +
                ", numFavorites=" + numFavorites +
                ", comments=" + comments +
                '}';
    }
}
