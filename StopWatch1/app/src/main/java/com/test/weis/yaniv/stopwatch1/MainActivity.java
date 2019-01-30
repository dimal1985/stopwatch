package com.test.weis.yaniv.stopwatch1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler;


    /**
     * This is the main method of the program, it runs only once, and starts up everything.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

      // CREATING THE FIRST WATCH //////////////////////////
        createNewWatchBasedOn(R.id.tvTimer,
                R.id.btlist,
                R.id.btStart,
                R.id.btPause,
                R.id.btReset,
                R.id.btlap);


        // CREATING THE SECONF WATCH ///////////////////////////

        createNewWatchBasedOn(R.id.tvTimer2,
                R.id.btlist2,
                R.id.btStart2,
                R.id.btPause2,
                R.id.btReset2,
                R.id.btlap2);

    }

    private void createNewWatchBasedOn(int timerTextViewId,
                                       int lapsListTextViewId,
                                       int startButtonId,
                                       int pauseButtonId,
                                       int resetButtonId,
                                       int lapButtonId) {
        TextView timerTextView = findViewById(timerTextViewId);
        TextView lapsListTextView = findViewById(lapsListTextViewId);
        Button startButton = findViewById(startButtonId);
        Button pauseButton = findViewById(pauseButtonId);
        Button resetButton = findViewById(resetButtonId);
        Button lapButton = findViewById(lapButtonId);

        createNewStopWatch(timerTextView, lapsListTextView, startButton, pauseButton, resetButton, lapButton);
    }

    private void createNewStopWatch(TextView timerTextView, TextView lapsListTextView,
                                    Button start, Button pause, Button reset, Button lap) {
        new StopWatchInternal(handler, start, pause, reset, lap, timerTextView, lapsListTextView);
    }

}