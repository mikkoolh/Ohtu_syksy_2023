package com.automaatio.utils;

/**
 * User input validation
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FormInputValidator {
    private final Integer USERNAME_MIN_LENGTH, PASSWORD_MIN_LENGTH, NAME_MIN_LENGTH, USERNAME_MAX_LENGTH, PASSWORD_MAX_LENGTH, NAME_MAX_LENGTH, PHONE_MIN_LENGTH, PHONE_MAX_LENGTH;

    /**
     * Constructor
     */
    public FormInputValidator() {
        // SAA VAIHTAA!!!
        USERNAME_MIN_LENGTH = 5;
        USERNAME_MAX_LENGTH = 20;

        PASSWORD_MIN_LENGTH = 8;
        PASSWORD_MAX_LENGTH = 20;

        NAME_MIN_LENGTH = 1;
        NAME_MAX_LENGTH = 20;

        PHONE_MIN_LENGTH = 7;
        PHONE_MAX_LENGTH = 15;
    }


    /**
     * Checks if the given values are the right length.
     * @param value
     * @param type
     * @return
     */

    public Boolean isLengthCorrect(String value, String type){

        if (type.equals("username")){
            return value.length() >= USERNAME_MIN_LENGTH && value.length() <= USERNAME_MAX_LENGTH;

        } else if (type.equals("name")) {
            return value.length() >= NAME_MIN_LENGTH && value.length() <= NAME_MAX_LENGTH;
        } else if (type.equals("password")){
            return value.length() >= PASSWORD_MIN_LENGTH && value.length() <= PASSWORD_MAX_LENGTH;

        }else {
            return false;
        }
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

    public Integer getNAME_MIN_LENGTH() {
        return NAME_MIN_LENGTH;
    }

    public Integer getUSERNAME_MAX_LENGTH() {
        return USERNAME_MAX_LENGTH;
    }

    public Integer getPASSWORD_MAX_LENGTH() {
        return PASSWORD_MAX_LENGTH;
    }

    public Integer getNAME_MAX_LENGTH() {
        return NAME_MAX_LENGTH;
    }

    public Integer getPHONE_MIN_LENGTH() {
        return PHONE_MIN_LENGTH;
    }

    public Integer getPHONE_MAX_LENGTH() {
        return PHONE_MAX_LENGTH;
    }
}