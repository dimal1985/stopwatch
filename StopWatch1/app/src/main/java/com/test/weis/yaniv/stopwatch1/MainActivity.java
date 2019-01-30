package com.test.weis.yaniv.stopwatch1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, lapsListTextView;
    Button start, pause, reset, lap;

    Handler handler;


    /**
     * This is the main method of the program, it runs only once, and starts up everything.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        timerTextView = findViewById(R.id.tvTimer);
        lapsListTextView = findViewById(R.id.btlist);

        //buttons on the screen
        start = findViewById(R.id.btStart);
        pause = findViewById(R.id.btPause);
        reset = findViewById(R.id.btReset);
        lap = findViewById(R.id.btbutton3);

        createNewStopWatch(timerTextView, lapsListTextView, start, pause, reset, lap);
    }

    private void createNewStopWatch(TextView timerTextView, TextView lapsListTextView,
                                    Button start, Button pause, Button reset, Button lap) {
        new StopWatchInternal(handler, start, pause, reset, lap, timerTextView, lapsListTextView);
    }

}