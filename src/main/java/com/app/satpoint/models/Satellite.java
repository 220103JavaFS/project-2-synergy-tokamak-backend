package com.app.satpoint.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "satId")
@Entity
@Table(name = "satellites", uniqueConstraints = {@UniqueConstraint(columnNames = {"noradId"})})
public class Satellite implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long satId;

    @Column(nullable = false, unique = true)
    private int noradId;

    @Column(nullable = false, unique = true)
    private String satName;
    private String satPicture; //Url for sat picture
    private int numFavorites;

    //is set to true when returning if the user who requested this satellite has it in their favorites
    @Transient
    private boolean isFavorite = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "satellite")
    @JsonManagedReference
    private Set<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_favorites",
            joinColumns = @JoinColumn(name = "favorites_sat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> favedBy;



    public Satellite() {
    }


    public Satellite(long satId, int noradId, String satName, String satPicture, int numFavorites,
                     Set<Comment> comments, List<User> favedBy) {
        this.satId = satId;
        this.noradId = noradId;
        this.satName = satName;
        this.satPicture = satPicture;
        this.numFavorites = numFavorites;
        this.comments = comments;
        this.favedBy = favedBy;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getFavedBy() {
        return favedBy;
    }

    public void setFavedBy(List<User> favedBy) {
        this.favedBy = favedBy;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    //Keep comparison of sats as just satId and noradId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Satellite satellite = (Satellite) o;
        return satId == satellite.satId && noradId == satellite.noradId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(satId, noradId, satName, satPicture, numFavorites);
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
