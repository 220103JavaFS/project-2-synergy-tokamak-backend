package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "satellites")
public class Satellite {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long satID;

    @Column(nullable = false, unique = true)
    private String satName;
    private Byte[] satPicture; //might use a URL instead?
    private int numFavorites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "satellite")
    @JsonManagedReference
    private List<Comment> comments;

    public Satellite() {
    }

    @Autowired
    public Satellite(long satID, String satName, Byte[] satPicture, int numFavorites, List<Comment> comments) {
        this.satID = satID;
        this.satName = satName;
        this.satPicture = satPicture;
        this.numFavorites = numFavorites;
        this.comments = comments;
    }

    public long getSatID() {
        return satID;
    }

    public void setSatID(long satID) {
        this.satID = satID;
    }

    public String getSatName() {
        return satName;
    }

    public void setSatName(String satName) {
        this.satName = satName;
    }

    public Byte[] getSatPicture() {
        return satPicture;
    }

    public void setSatPicture(Byte[] satPicture) {
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
                "satID=" + satID +
                ", satName='" + satName + '\'' +
                ", satPicture=" + Arrays.toString(satPicture) +
                ", numFavorites=" + numFavorites +
                ", comments=" + comments +
                '}';
    }
}
