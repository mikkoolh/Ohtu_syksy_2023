package com.automaatio.model.database;

/**
 * @author Mikko Hänninen
 * @author Matleena Kankaanpää
 * 15.09.2023
 *
 * User data
 */

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    /*
    @OneToMany(mappedBy ="user" )
    @Column(name = "history_events")
    private List<HistoryEvents> historyEvents;


     */
    @Column(name = "age")
    private int age;

    @Column(name = "userType")
    private int userType;

    /**
     * Save a new user
     *
     * @param username
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param password
     * @param age

     */
//   * @param userType

    public void saveUser(String username, String firstName, String lastName, String phoneNumber, String email, String password, int age, int userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber =phoneNumber;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    /**
     * Parameterless constructor
     */
    public User() {}

    /**
     * Parameterized constructor
     * @param username
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param password
     * @param age
     * @param userType
     */
    public User(String username, String firstName, String lastName, String phoneNumber, String email, String password, int age, int userType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.age = age;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }



    /*
    public List<HistoryEvents> getHistoryEvents() {
        return historyEvents;
    }

    public void setHistory(List<HistoryEvents> historyEvents) {
        this.historyEvents = historyEvents;
    }

     */
}