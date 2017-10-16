package com.example.jay.u_dirty_rat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;


/**
 * Created by jw_chung on 10/15/17.
 */

public class ReportDB {
    //loading in CSV file as an array list of rat class objects.

    List database = new ArrayList();
    InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
    BufferedReader reader = new BufferedReader
            (new InputStreamReader(inputStream));
        try {
        String rawreport;
        while((rawreport = reader.readLine()) != null) {
            String[] pieces = rawreport.split(",");
            Rat report = new Rat(Integer.parseInt(pieces[0]),
                    pieces[1],
                    pieces[2],
                    Integer.parseInt(pieces[3]),
                    pieces[4],
                    pieces[5],
                    pieces[6],
                    Double.parseDouble(pieces[7]),
                    Double.parseDouble(pieces[8]));
            database.add(report);
        }
    } catch (IOException e) {
    }
}
