package com.automaatio.utils;

/**
 * User input validation
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FormInputValidator {
    private final Integer USERNAME_MIN_LENGTH, PASSWORD_MIN_LENGTH, FIRSTNAME_MIN_LENGTH, LASTNAME_MIN_LENGTH,
            USERNAME_MAX_LENGTH, PASSWORD_MAX_LENGTH, FIRSTNAME_MAX_LENGTH, LASTNAME_MAX_LENGTH, PHONE_MIN_LENGTH, PHONE_MAX_LENGTH;

    /**
     * Constructor
     */
    public FormInputValidator() {
        // SAA VAIHTAA!!!
        this.USERNAME_MIN_LENGTH = 5;
        this.USERNAME_MAX_LENGTH = 20;

        this.PASSWORD_MIN_LENGTH = 8;
        this.PASSWORD_MAX_LENGTH = 20;

        this.FIRSTNAME_MIN_LENGTH = 1;
        this.FIRSTNAME_MAX_LENGTH = 20;

        this.LASTNAME_MIN_LENGTH = 1;
        this.LASTNAME_MAX_LENGTH = 20;

        this.PHONE_MIN_LENGTH = 7;
        this.PHONE_MAX_LENGTH = 15;
    }

    /**
     * Checks if the length of the username provided is within range
     * @param username Username input
     * @return Boolean True if the username length is within range
     */
    public Boolean usernameLengthCorrect(String username) {
        return (username.length() >= USERNAME_MIN_LENGTH && username.length() <= USERNAME_MAX_LENGTH);
    }

    /**
     * Checks if the length of the password provided is within range
     * @param password Password input
     * @return Boolean True if the password length is within range
     */
    public Boolean passwordLengthCorrect(String password) {
        return (password.length() >= PASSWORD_MIN_LENGTH && password.length() <= PASSWORD_MAX_LENGTH);
    }

    /**
     * Checks if the length of the first name provided is within range
     * @param firstName First name input
     * @return Boolean True if the first name length is within range
     */
    public Boolean firstNameLengthCorrect(String firstName) {
        return (firstName.length() >= FIRSTNAME_MIN_LENGTH && firstName.length() <= FIRSTNAME_MAX_LENGTH);
    }

    /**
     * Checks if the length of the last name provided is within range
     * @param lastName Last name input
     * @return Boolean True if the last name length is within range
     */
    public Boolean lastNameLengthCorrect(String lastName) {
        return (lastName.length() >= LASTNAME_MIN_LENGTH && lastName.length() <= LASTNAME_MAX_LENGTH);
    }

    /**
     * Checks if the email address provided is in a valid format
     * @param email Email address input
     * @return Boolean True if the email format is valid
     */
    public Boolean emailFormatCorrect(String email) {
        return email.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$");
    }

    /**
     * Checks if the phone number provided is in a valid format
     * @param phone Phone number input
     * @return Boolean True if the phone number format is valid
     */
    public Boolean phoneFormatCorrect(String phone) {
        //return phone.matches("^+?[0-9]{7,14}$");
        return phone.matches("^+?[0-9-]{" + PHONE_MIN_LENGTH + "," + PHONE_MAX_LENGTH + "}$");
    }


    public Integer getUSERNAME_MIN_LENGTH() {
        return USERNAME_MIN_LENGTH;
    }

    public Integer getPASSWORD_MIN_LENGTH() {
        return PASSWORD_MIN_LENGTH;
    }

    public Integer getFIRSTNAME_MIN_LENGTH() {
        return FIRSTNAME_MIN_LENGTH;
    }

    public Integer getLASTNAME_MIN_LENGTH() {
        return LASTNAME_MIN_LENGTH;
    }

    public Integer getUSERNAME_MAX_LENGTH() {
        return USERNAME_MAX_LENGTH;
    }

    public Integer getPASSWORD_MAX_LENGTH() {
        return PASSWORD_MAX_LENGTH;
    }

    public Integer getFIRSTNAME_MAX_LENGTH() {
        return FIRSTNAME_MAX_LENGTH;
    }

    public Integer getLASTNAME_MAX_LENGTH() {
        return LASTNAME_MAX_LENGTH;
    }

    public Integer getPHONE_MIN_LENGTH() {
        return PHONE_MIN_LENGTH;
    }

    public Integer getPHONE_MAX_LENGTH() {
        return PHONE_MAX_LENGTH;
    }
}