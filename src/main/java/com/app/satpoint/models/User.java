package com.app.satpoint.models;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(int userId, String username, String password, String firstName, String lastName, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserId() == user.getUserId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), getFirstName(), getLastName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}