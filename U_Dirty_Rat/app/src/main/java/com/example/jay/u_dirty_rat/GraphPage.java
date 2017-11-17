package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.jay.u_dirty_rat.WelcomeScreen.database;

public class GraphPage extends AppCompatActivity {

    BarGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);


        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        GraphView graph = (GraphView) findViewById(R.id.graph);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText raw = (EditText) findViewById(R.id.yearText);
                String selectedYear = raw.getText().toString().substring(2,4);
                int[] dataTable = new int[12];
                if(selectedYear.length() == 2) {
                    for(int i = 0; i < database.size(); i++) {
                        Rat rat = (Rat) database.get(i);
                        Date date = rat.getDate();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);  //use java.util.Date object as arguement
                        // get the value of all the calendar date fields.
                        System.out.println("Calendar's Year: " + cal.get(Calendar.YEAR));
                        System.out.println("Calendar's Month: " + cal.get(Calendar.MONTH));
                        System.out.println("Calendar's Day: " + cal.get(Calendar.DATE));
                        //String[] pieces = date.split("/",-1);
                        //if (pieces.length == 3) {
                            //Log.i(date.,"debug");
                     //       if (pieces[2].substring(0,2).equals(selectedYear)) { //yyyy
                                int month = cal.get(Calendar.MONTH); //mm
                                if(dataTable[month] != 0) {
                                    int count = dataTable[month];
                                    count ++;
                                    dataTable[month] = count;
                                } else {
                                    dataTable[month] = 1;
                                }
                 //           }
                        //}
                    }
                    DataPoint[] list = new DataPoint[12];
                    for (int index= 0 ; index < dataTable.length; index++) {
                        list[index] = new DataPoint(index+1, dataTable[index]);
                    }
                    series = new BarGraphSeries<DataPoint>(list);
                    graph.addSeries(series);
                    graph.setTitle("Historical Sighting Report");
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.getViewport().setMinX(0);
                    graph.getViewport().setMaxX(13);
                    graph.getGridLabelRenderer().setHorizontalAxisTitle("month");
                    graph.getGridLabelRenderer().setVerticalAxisTitle("Sightings");
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GraphPage.this,MainPage.class));
            }
        });
    }
}
