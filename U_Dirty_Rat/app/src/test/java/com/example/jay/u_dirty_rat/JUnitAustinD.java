package com.example.jay.u_dirty_rat;

import org.junit.Test;
import org.junit.Assert;

/**
 * jUnit test for contains(String email) method.
 */

public class JUnitAustinD {
    private UserDB db = UserDB.getInstance();

    @Test
    public void containsTest_isCorrect() throws Exception {
        String email = "aaustin.d@gmail.com";
        Assert.assertEquals(true, UserDB.contains(email));
    }

    @Test
    public void containsTest_noEmail() throws Exception {
        String email = "";
        Assert.assertEquals(false, UserDB.contains(email));
    }

    @Test
    public void containsTest_isIncorrect() throws Exception {
        String email = "aaustind@gmail.com";
        Assert.assertEquals(false, UserDB.contains(email));
    }
}
