package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * Author: Nikita Nossenko
 *
 * This class represents a UserType entity that is stored in the database.
 */

@Entity
@Table(name = "userType")
public class UserType {
    /**
     * The unique identifier for the userType.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTypeID") // Corrected column name
    private int userTypeID;

    /**
     * The description of user type.
     */
    @Column(name = "description")
    private String description;

    /**
     * Default constructor for creating a new userType instance.
     */
    public UserType() {}

    /**
     * Constructs a new UserType with the specified description.
     *
     * @param description The description of the user type.
     */
    public UserType(String description) {
        this.description = description;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}