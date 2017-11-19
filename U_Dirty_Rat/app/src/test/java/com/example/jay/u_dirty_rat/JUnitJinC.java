package com.example.jay.u_dirty_rat;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 * Junit test for isUsernameValid(String in) method.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitJinC {
    private final RegistrationActivity r = new RegistrationActivity();

    @Test
    public void nameCheckingTest_correct() throws Exception {
        String email = "jchung8@gmail.com";
        Assert.assertEquals(true, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_void() throws Exception {
        String email = "";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_noAt() throws Exception {
        String email = "jchung8gmail.com";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_noCompany() throws Exception {
        String email = "jchung8@.com";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_noDot() throws Exception {
        String email = "jchung8@com";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_noBoth() throws Exception {
        String email = "jchung8";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }

    @Test
    public void nameCheckingTest_multipleAt() throws Exception {
        String email = "jchung8@gma@il.com";
        Assert.assertEquals(false, r.isUsernameValid(email));
    }


}