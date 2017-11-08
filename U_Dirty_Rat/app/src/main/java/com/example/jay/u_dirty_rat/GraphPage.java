package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.jay.u_dirty_rat.WelcomeScreen.database;

public class GraphPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);


        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        String selectedYear = ((EditText) findViewById(R.id.yearText)).toString();
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>();


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<Integer,Integer> dataTable = new HashMap<>();
                if(selectedYear.length() == 4) {
                    for (Rat report : database) {
                        String date = report.getDate();
                        if (date.substring(5).equals(selectedYear)) { //yyyy
                            int month = Integer.parseInt(date.substring(0,1)); //mm
                            if(dataTable.containsKey(month)) {
                                int count = dataTable.get(month);
                                count ++;
                                dataTable.put(month,count);
                            } else {
                                dataTable.put(month,1);
                            }
                        }
                    }
                    BarGraphSeries<DataPoint> series;
                    DataPoint[] plot = new DataPoint[12];
                    for (int i=1; i<=12; i++) {
                        if(dataTable.containsKey(i)) {
                            plot[i] = new DataPoint(i,dataTable.get(i));
                        } else {
                            plot[i] = new DataPoint(i,0);
                        }
                    }
                    series = new BarGraphSeries(plot);
                    graph.addSeries(series);
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
