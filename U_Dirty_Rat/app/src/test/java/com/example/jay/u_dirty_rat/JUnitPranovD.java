package com.example.jay.u_dirty_rat;

import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by Pranov on 11/20/17.
 */

public class JUnitPranovD {
    private final WelcomeScreen r = new WelcomeScreen();
    @Test
    public void parseDateTest_correct() throws Exception {
        String date = "09/04/12";
        Date answer = new Date(1346731200000l);
        Assert.assertEquals(answer,r.parseDate(date));
    }

    @Test
    public void parseDateTest_void(){
        String date = "";

        Assert.assertEquals(null, r.parseDate(date));
    }

    @Test
    public void parseDateTest_wrong(){
        String date = "09-04-12";

        Assert.assertEquals(null, r.parseDate(date));
    }
}
