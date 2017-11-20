package com.example.jay.u_dirty_rat;

import java.util.ArrayList;

/**
 * Singleton class used as a database
 */

public class UserDB {

    private static UserDB INSTANCE = null;
    private static ArrayList<String> emails = new ArrayList<>(8);
    private static ArrayList<String> passwords = new ArrayList<>(8);
    private static ArrayList<Boolean> areAdmins = new ArrayList<>(8);

    private UserDB() {}

    /**
     * brings user database from firebase.
     * @return user DB to use
     */
    public static UserDB getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDB();
        }
        return INSTANCE;
    }

    /**
     * checks if the email is in the list
     * @param email the email to be checked
     * @return boolean value (true if the list contains it)
     */
    public static boolean contains(String email) {
        return emails.contains(email);
    }

    /**
     * checks if email and password match.
     * @param email the email to be checked
     * @param password the password to be checked
     * @return true if correct password for email is there.
     */
    public static boolean isCorrectPassword(String email, String password) {
        return password.equals(passwords.get(emails.indexOf(email)));
    }

    /**
     * add the user with input attribute to the list.
     * @param email the email to be added
     * @param password the password to be added
     * @param isAdmin the value that determines if a user is an admin or not
     */
    public static void add(String email, String password, boolean isAdmin) {
        emails.add(email);
        passwords.add(password);
        areAdmins.add(isAdmin);
    }

}
