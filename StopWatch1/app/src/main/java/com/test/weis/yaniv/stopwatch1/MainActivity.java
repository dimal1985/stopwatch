package com.test.weis.yaniv.stopwatch1;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    TextView timer, lapsListOutput;
    Button start, pause, reset, lap;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    List<Long> lapsList = new CopyOnWriteArrayList<>();

    /**
     * This is the main method of the program, it runs only once, and starts up everything.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.tvTimer);
        lapsListOutput = (TextView) findViewById(R.id.btlist);

        //buttons on the screen
        start = (Button) findViewById(R.id.btStart);
        pause = (Button) findViewById(R.id.btPause);
        reset = (Button) findViewById(R.id.btReset);
        lap = (Button) findViewById(R.id.btbutton3);

        handler = new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;

                lapsList.clear();

                timer.setText("00:00:00");
                lapsListOutput.setText("");

            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeUntilNow = (SystemClock.uptimeMillis() - StartTime) + TimeBuff;
                lapsList.add(timeUntilNow);
            }

        });
    }

    public Runnable runnable = new Runnable() {

        /**
         * this run() method is executed in loops over and over again, since start butrton pressed, until pause buitton pressed
         */
        public void run() {
            // **** THIS IS THE REPEATED LOGIC *********
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            timer.setText(getStringTime(UpdateTime));
            printLaps();
            // **** UP UNTILL HERE *******

            //this line is responsible to rerun the run() method right after it finishes
            handler.postDelayed(this, 0);
        }

    };

    /**
     * This methgetStringTime(lap);od sets the laps output text into the lapsListOutput TextView variable
     */
    public void printLaps() {
        int lapsNumber = lapsList.size();
        String lapsOutputStr = "";
        for (int i = 0; i < lapsNumber; i++) {
            long lap = lapsList.get(i);
            String lapStr = getStringTime(lap);
            lapsOutputStr = lapsOutputStr + "Lap " + (i+1) + ": " + lapStr + "\n";
        }
        lapsListOutput.setText(lapsOutputStr);
    }

    /**
     * helper function: gets time period in milliseconds, and returns human readable representation of it,
     * in format: "MM:SS:mmm"
     */
    private String getStringTime(long timePeriodMillis) {
        int seconds, minutes, milliSeconds;
        seconds = (int) (timePeriodMillis / 1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        milliSeconds = (int) (timePeriodMillis % 1000);

        return "" + minutes + ":"
                + String.format("%02d", seconds) + ":"
                + String.format("%03d", milliSeconds);
    }

}