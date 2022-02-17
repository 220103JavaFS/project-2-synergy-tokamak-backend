package com.app.satpoint.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    public String password;
    private String firstName;
    private String lastName;
    private String email;
    private String aboutMe;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "favedBy")
    @JsonIgnore
    private Set<Satellite> favorites;

    public User() {
    }

    public User(long id, String username, String password, String firstName, String lastName, String email, Set<Satellite> favorites, String aboutMe) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.favorites = favorites;
        this.aboutMe = aboutMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Satellite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Satellite> favorites) {
        this.favorites = favorites;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, aboutMe, favorites);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", AboutMe='" + aboutMe + '\'' +
                ", favorites=" + favorites +
                '}';
    }
}
