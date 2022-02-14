package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "satellites", uniqueConstraints = {@UniqueConstraint(columnNames = {"noradId"})})
public class Satellite {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long satId;

    @Column(nullable = false, unique = true)
    private int noradId;

    @Column(nullable = false, unique = true)
    private String satName;
    private String satPicture; //Url for sat picture
    private int numFavorites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "satellite")
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToMany
    private List<User> favedBy;

    public Satellite() {
    }


    public Satellite(long satId, int noradId, String satName, String satPicture, int numFavorites, List<Comment> comments) {
        this.satId = satId;
        this.noradId = noradId;
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

    public int getNoradId() {
        return noradId;
    }

    public void setNoradId(int noradId) {
        this.noradId = noradId;
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

    public void incrementFavorites(){
        this.numFavorites++;
    }

    public void decrementFavorites(){
        this.numFavorites = Math.max(0, --numFavorites);
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
