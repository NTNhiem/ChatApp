package Models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private Gender gender;
    private Date dob;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String password;
    private Map<String, User> friends;
    private List<Relationship> relationships;

    public User(String id, Gender gender, Date dob, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.gender = gender;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName;
        this.username = username;
        this.password = password;
        this.groups = new ArrayList<>();
        this.friends = new HashMap<>();
        this.relationships = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName();
    }

    public String getFullName() {return fullName; }

    public void setFullName() { this.fullName = firstName + lastName; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }
}
