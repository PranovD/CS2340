package com.example.jay.u_dirty_rat;

import java.util.ArrayList;

public class UserDB {

    private static UserDB INSTANCE = null;
    private static ArrayList<String> emails = new ArrayList<>(8);
    private static ArrayList<String> passwords = new ArrayList<>(8);
    private static ArrayList<Boolean> areAdmins = new ArrayList<>(8);

    private UserDB() {};

    public static UserDB getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDB();
        }
        return INSTANCE;
    }

    public static boolean contains(String email) {
        return emails.contains(email);
    }

    public static boolean isCorrectPassword(String email, String password) {
        return password.equals(passwords.get(emails.indexOf(email)));
    }

    public static void add(String email, String password, boolean isAdmin) {
        emails.add(email);
        passwords.add(password);
        areAdmins.add(isAdmin);
    }

}
