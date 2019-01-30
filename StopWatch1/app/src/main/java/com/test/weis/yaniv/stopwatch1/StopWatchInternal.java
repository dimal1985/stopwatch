package com.test.weis.yaniv.stopwatch1;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StopWatchInternal {
    Button start, pause, reset, lap;
    TextView timer, lapsListOutput;

    List<Long> lapsList = new CopyOnWriteArrayList<>();

    long millisecondTime, startTime, timeBuff, updateTime = 0L;

    Handler handler;

    Utils utils = Utils.getInstance();

    public StopWatchInternal(Handler handler,
                             Button start,
                             Button pause,
                             Button reset,
                             Button lap,
                             TextView timer,
                             TextView lapsListOutput) {
        this.handler = handler;
        this.start = start;
        this.pause = pause;
        this.reset = reset;
        this.lap = lap;
        this.timer = timer;
        this.lapsListOutput = lapsListOutput;

        initButtonsListeners();
    }

    private void initButtonsListeners() {

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeBuff += millisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                millisecondTime = 0L;
                startTime = 0L;
                timeBuff = 0L;
                updateTime = 0L;

                lapsList.clear();

                timer.setText("00:00:00");
                lapsListOutput.setText("");

            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeUntilNow = (SystemClock.uptimeMillis() - startTime) + timeBuff;
                lapsList.add(timeUntilNow);
            }

        });
    }

    private Runnable runnable = new Runnable() {

        /**
         * this run() method is executed in loops over and over again, since start butrton pressed, until pause buitton pressed
         */
        public void run() {
            // **** THIS IS THE REPEATED LOGIC *********
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            timer.setText(utils.getStringTime(updateTime));
            utils.printLaps(lapsList, lapsListOutput);
            // **** UP UNTILL HERE *******

            //this line is responsible to rerun the run() method right after it finishes
            handler.postDelayed(this, 0);
        }

    };
}
