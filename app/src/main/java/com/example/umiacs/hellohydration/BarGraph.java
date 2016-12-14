package com.example.umiacs.hellohydration;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.ArrayList;

/**
 * Created by umiacs on 12/1/16.
 * Ciations:
 * PhilJay/MPAndroidChart - github repo to help make charts
 *
 */

public class BarGraph extends AppCompatActivity /*implements OnChartValueSelectedListener*/ {
    BarChart barChart;
    private int checkView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_bargraph);
        /*Cregate the bar chart
         * If no data is recorded this should say "Start drinking!"
         */
        barChart = (BarChart) findViewById(R.id.bargraph);
        barChart.setNoDataText("Start drinking!");
        getSupportActionBar().setTitle("Weekly Progress");
        checkView = 0;
        setBars();
    }

    private void setBars() {
        /*Varibale created to contain all the entries in this graph
        * Hard coded some data for the prototype
        * */
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 4.5f));
        barEntries.add(new BarEntry(2, 8f));
        barEntries.add(new BarEntry(3, 6f));
        barEntries.add(new BarEntry(4, 12f));
        barEntries.add(new BarEntry(5, 18f));
        barEntries.add(new BarEntry(6, 9f));
        barEntries.add(new BarEntry(7, 20f));
        /*This created the data set to be plotted,
        * if legend was showed this would display water consumption*/
        BarDataSet barDataSet = new BarDataSet(barEntries, "Water Consumption");
        barDataSet.setHighlightEnabled(true);
        BarData barData = new BarData(barDataSet);

        /*Makes all the bars the same size*/
        barChart.setFitBars(true);
        barChart.setData(barData);

        barData.setBarWidth(.96f);
        /*Hides the legend because only displaying one measure*/
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        barChart.setHighlightFullBarEnabled(true);

        /*Allows users to pitch to zoom*/
        barChart.setScaleEnabled(true);
        barChart.setTouchEnabled(true);
        /*Gets the xAxis*/
        XAxis xAxis = barChart.getXAxis();
        /*Changes the numbers on the xAxis to days of the week
        * by getting passed into an axis formatter class*/
        String[] xLabels = {"", "Su", "M", "T", "W", "Th", "F", "Sa"};
        xAxis.setValueFormatter(new MyAxisFormatter(xLabels));
        /*Options selected -
        * draw the axis line and grid lines
        * set the minimum x value to zero
        * display the days of the week on the bottom of the graph
        **/
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.GRAY);
        xAxis.setTextSize(12f);

        /*Y-Axis options
        * Hide the right side of the axis
        * Set the minimum value to 0
        * */
        YAxis yLeftAxis = barChart.getAxisLeft();
        //YAxis yRightAxis = barChart.getAxisRight();
        barChart.getAxisRight().setEnabled(false);
        yLeftAxis.setAxisMinimum(0f);
        /*Add a limit line to designated reached goals
        * Options:
        *  Change text color to navy
        *  Make text size 14
        *  Make line color navy
        * */
        LimitLine goal = new LimitLine(10f, "Goal Reached!");
        goal.setLineWidth(3f);
        goal.setTextColor(Color.rgb(4, 50, 124));
        goal.setTextSize(14f);
        goal.setLineColor(Color.rgb(4, 50, 124));
        yLeftAxis.addLimitLine(goal);

        /*Draw the axis line, and draw left gridlines*/
        yLeftAxis.setDrawAxisLine(true);
        yLeftAxis.setLabelCount(10, true);
        yLeftAxis.setDrawGridLines(true); //  grid lines
        yLeftAxis.setTextColor(Color.GRAY);
        yLeftAxis.setTextSize(14f);
        //yRightAxis.setDrawGridLines(false); // no grid lines
        /*Take away description*/
        barChart.setDescription(null);
        //set a listener if bars are clicked
        barChart.setHighlightPerTapEnabled(true);
        barChart.setClickable(true);


        /*Set values for exercise based on where click occurs,
         * i.e. what bar/day of the week
         * */
        barChart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        checkView = 1;
                        setContentView(R.layout.activity_exercisetracker);
                        TextView bikingTime = (TextView) findViewById(R.id.bikingTime);
                        TextView walkingTime = (TextView) findViewById(R.id.walkingTime);
                        TextView runningTime = (TextView) findViewById(R.id.runningTime);
                        TextView date = (TextView) findViewById(R.id.currentDate);
                        /*Makes buttons invisible, so user does not click*/
                        ImageView button = (ImageView) findViewById(R.id.trackbutton);
                        button.setVisibility(View.INVISIBLE);
                        // ImageView arrowRight = (ImageView) findViewById(R.id.arrow1);
                        //ImageView arrowLeft = (ImageView) findViewById(R.id.arrow2);
                        //arrowRight.setVisibility(View.INVISIBLE);
                        //arrowLeft.setVisibility(View.INVISIBLE);
                        TextView timer = (TextView) findViewById(R.id.timer);
                        timer.setVisibility(View.INVISIBLE);
                        Log.d("TOUCHX", Float.toString(event.getX()));
                        if (event.getX() <= 222.31) {
                            date.setText("Sun., Nov. 27th");
                            runningTime.setText("00:00");
                            bikingTime.setText("00:15");
                            walkingTime.setText("01:00");
                        } else if (event.getX() > 222.31 && event.getX() <= 325.41) {
                            date.setText("Mon., Nov. 28th");
                            runningTime.setText("01:00");
                            bikingTime.setText("00:25");
                            walkingTime.setText("02:00");
                        } else if (event.getX() > 321.41 && event.getX() <= 427.48) {
                            date.setText("Tues., Nov. 29th");
                            runningTime.setText("00:00");
                            bikingTime.setText("00:35");
                            walkingTime.setText("01:00");
                        } else if (event.getX() > 427.48 && event.getX() <= 529.59) {
                            date.setText("Wed., Nov. 30th");
                            runningTime.setText("00:45");
                            bikingTime.setText("01:45");
                            walkingTime.setText("00:30");
                        } else if (event.getX() >= 529.59 && event.getX() <= 628.66) {
                            date.setText("Thurs., Dec 1st");
                            runningTime.setText("00:45");
                            bikingTime.setText("00:35");
                            walkingTime.setText("03:00");
                        } else if (event.getX() >= 628.66 && event.getX() <= 734.76) {
                            date.setText("Fri., Dec 2nd");
                            runningTime.setText("01:25");
                            bikingTime.setText("00:45");
                            walkingTime.setText("02:00");
                        } else {
                            date.setText("Sat., Dec 3rd");
                            runningTime.setText("02:45");
                            bikingTime.setText("00:15");
                            walkingTime.setText("00:45");
                        }
                }

                //Intent activityTracker = new Intent(BarGraph.this, ExerciseTracker.class);
                //startActivity(activityTracker);

                return true;

            }
        });

        /*since we are drawing the graph, call invalidate()*/
        barChart.invalidate();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void barsBack(View v) {


        /*Varibale created to contain all the entries in this graph
        * Hard coded some data for the prototype
        * */
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(1, 18.5f));
                barEntries.add(new BarEntry(2, 20f));
                barEntries.add(new BarEntry(3, 5f));
                barEntries.add(new BarEntry(4, 22f));
                barEntries.add(new BarEntry(5, 28f));
                barEntries.add(new BarEntry(6, 10f));
                barEntries.add(new BarEntry(7, 24f));
        /*This created the data set to be plotted,
        * if legend was showed this would display water consumption*/
                BarDataSet barDataSet = new BarDataSet(barEntries, "Water Consumption");
                barDataSet.setHighlightEnabled(true);
                BarData barData = new BarData(barDataSet);

        /*Makes all the bars the same size*/
                barChart.setFitBars(true);
                barChart.setData(barData);

                barData.setBarWidth(.96f);
        /*Hides the legend because only displaying one measure*/
                Legend legend = barChart.getLegend();
                legend.setEnabled(false);
                barChart.setHighlightFullBarEnabled(true);

        /*Allows users to pitch to zoom*/
                barChart.setScaleEnabled(true);
                barChart.setTouchEnabled(true);
        /*Gets the xAxis*/
                XAxis xAxis = barChart.getXAxis();
        /*Changes the numbers on the xAxis to days of the week
        * by getting passed into an axis formatter class*/
                String[] xLabels = {"", "Su", "M", "T", "W", "Th", "F", "Sa"};
                xAxis.setValueFormatter(new MyAxisFormatter(xLabels));
        /*Options selected -
        * draw the axis line and grid lines
        * set the minimum x value to zero
        * display the days of the week on the bottom of the graph
        **/
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);
                xAxis.setAxisMinimum(0f);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextColor(Color.GRAY);
                xAxis.setTextSize(12f);

        /*Y-Axis options
        * Hide the right side of the axis
        * Set the minimum value to 0
        * */
                YAxis yLeftAxis = barChart.getAxisLeft();
                //YAxis yRightAxis = barChart.getAxisRight();
                barChart.getAxisRight().setEnabled(false);
                yLeftAxis.setAxisMinimum(0f);
        /*Add a limit line to designated reached goals
        * Options:
        *  Change text color to navy
        *  Make text size 14
        *  Make line color navy
        * */
                LimitLine goal = new LimitLine(20f, "Goal Reached!");
                goal.setLineWidth(3f);
                goal.setTextColor(Color.rgb(4, 50, 124));
                goal.setTextSize(14f);
                goal.setLineColor(Color.rgb(4, 50, 124));
               // yLeftAxis.addLimitLine(goal);

        /*Draw the axis line, and draw left gridlines*/
                yLeftAxis.setDrawAxisLine(true);
                yLeftAxis.setLabelCount(10, true);
                yLeftAxis.setDrawGridLines(true); //  grid lines
                yLeftAxis.setTextColor(Color.GRAY);
                yLeftAxis.setTextSize(14f);
                //yRightAxis.setDrawGridLines(false); // no grid lines
        /*Take away description*/
                barChart.setDescription(null);
                //set a listener if bars are clicked
                barChart.setHighlightPerTapEnabled(true);
                barChart.setClickable(true);
                TextView date = (TextView) findViewById(R.id.date);
                date.setText("Sun Nov 27 - Sat Dec 3");
                barChart.invalidate();
            }


    /*ensure that back after clicking a bar graph
    will take you back to bargraph page*/
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (checkView == 1){
            startActivity(new Intent(BarGraph.this, BarGraph.class));
            finish();
        }

        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //startActivity(new Intent(BarGraph.this, BarGraph.class));
        //finish();

    }

    /*This private class allows us to change the x-Axis labels to days
    * of the week instead of numbers*/
    private class MyAxisFormatter implements IAxisValueFormatter {

        private String[] xLabels;

        public MyAxisFormatter(String[] values) {
            xLabels = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return xLabels[(int) value];
        }
    }

    //sets up toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //handles selected toolbar menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case(R.id.action_settings):
                intent = new Intent(BarGraph.this, Settings.class);
                startActivity(intent);
                break;
            case(R.id.action_exercisetracker):
                intent = new Intent(BarGraph.this, ExerciseTracker.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
