package com.example.jay.u_dirty_rat;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;


/** add some explanation.
 * Created by Pranov on 11/20/17.
 */

public class JUnitPranovD {
    private final WelcomeScreen r = new WelcomeScreen();
    @Test
    public void parseDateTest_correct() throws Exception {
        String date = "09/04/12";
        Date answer = new Date(1346731200000L);
        Assert.assertEquals(answer, WelcomeScreen.parseDate(date));
    }

    @Test
    public void parseDateTest_void(){
        String date = "";

        Assert.assertEquals(null, WelcomeScreen.parseDate(date));
    }

    @Test
    public void parseDateTest_wrong(){
        String date = "09-04-12";

        Assert.assertEquals(null, WelcomeScreen.parseDate(date));
    }
}
