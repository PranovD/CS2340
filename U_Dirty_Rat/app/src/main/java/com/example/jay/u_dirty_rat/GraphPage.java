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


import static com.example.jay.u_dirty_rat.WelcomeScreen.database;

public class GraphPage extends AppCompatActivity {

    private BarGraphSeries<DataPoint> series;

    /**
     * Creates the activity. Initializes an empty graph with the correct layout and formatting
     * @param savedInstanceState the stored activity data if returning from background
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);


        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        //Format and add the graph
        graph.addSeries(series);
        graph.setTitle("Historical Sighting Report");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Sightings");


<<<<<<< HEAD
        updateButton.setOnClickListener(view -> {
            EditText raw = (EditText) findViewById(R.id.yearText);
            String selectedYear = raw.getText().toString().substring(2, 4);
            int[] dataTable = new int[12];
            if(selectedYear.length() == 2) {
                for(int i = 0; i < database.size(); i++) {
                    Rat rat = (Rat) database.get(i);
                    String date = rat.getDate();
                    String[] pieces = date.split("/",-1);
                    if (pieces.length == 3) {
                        Log.i(pieces[2],"debug");
                        if (pieces[2].substring(0, 2).equals(selectedYear)) { //yyyy
                            int month = Integer.parseInt(pieces[0]); //mm
                            if(dataTable[month - 1] != 0) {
                                int count = dataTable[month-1];
                                count ++;
                                dataTable[month - 1] = count;
                            } else {
                                dataTable[month - 1] = 1;
=======
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText raw = (EditText) findViewById(R.id.yearText);
                String selectedYear = raw.getText().toString().substring(2, 4);
                int[] dataTable = new int[12];
                if(selectedYear.length() == 2) {
                    for(int i = 0; i < database.size(); i++) {
                        Rat rat = database.get(i);
                        String date = rat.getDate();
                        String[] pieces = date.split("/",-1);
                        if (pieces.length == 3) {
                            Log.i(pieces[2],"debug");
                            if (pieces[2].substring(0, 2).equals(selectedYear)) { //yyyy
                                int month = Integer.parseInt(pieces[0]); //mm
                                if(dataTable[month - 1] != 0) {
                                    int count = dataTable[month-1];
                                    count ++;
                                    dataTable[month - 1] = count;
                                } else {
                                    dataTable[month - 1] = 1;
                                }
>>>>>>> 62733c10817ad1de33809534f582f205df1678bf
                            }
                        }
                    }
                }
                DataPoint[] list = new DataPoint[12];
                for (int index= 0 ; index < dataTable.length; index++) {
                    list[index] = new DataPoint(index+1, dataTable[index]);
                }
                series.resetData(list);
            }

        });

        backButton.setOnClickListener(view -> startActivity(new Intent(GraphPage.this,MainPage.class)));
    }
}
