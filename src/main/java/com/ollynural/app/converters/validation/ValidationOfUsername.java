package com.ollynural.app.converters.validation;

/**
 * Checks the username is valid to use
 */
public class ValidationOfUsername {

    public String validateUsername(String username) {
        String newUsername = "";
        try {
            newUsername = username.toLowerCase();
            newUsername.trim();
            newUsername.replace(" ", "");
        } catch (Exception e) {
            System.out.println("How did you even manage this");
            e.printStackTrace();
        }
        return newUsername;
    }
}
