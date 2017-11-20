package com.example.jay.u_dirty_rat;

import org.junit.Test;
import org.junit.Assert;


/**
 * j unit test for password
 * Created by Jay on 11/20/17.
 */

public class JUnitJayP {

    private UserDB userDB = UserDB.getInstance();

    @Test
    public void containsTest_isCorrect() throws Exception {
        String password = "password";
        String email = "6jaypatel@gmail.com";
        Assert.assertEquals(true, userDB.isCorrectPassword(email, password));
    }

    @Test
    public void containsTest_noPassword() throws Exception {
        String password = "";
        String email = "6jaypatel@gmail.com";
        Assert.assertEquals(false, userDB.isCorrectPassword(email, password));
    }

    @Test
    public void containsTest_isIncorrect() throws Exception {
        String password = "pass";
        String email = "6jaypatel@gmail.com";
        Assert.assertEquals(false, userDB.isCorrectPassword(email, password));
    }


}

