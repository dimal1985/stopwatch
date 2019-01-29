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
    Runnable runLoop;

    public StopWatchInternal(Handler handler,
                             Runnable runLoop,
                             Button start,
                             Button pause,
                             Button reset,
                             Button lap,
                             TextView timer,
                             TextView lapsListOutput) {
        this.handler = handler;
        this.runLoop = runLoop;
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
                handler.postDelayed(runLoop, 0);

                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeBuff += millisecondTime;

                handler.removeCallbacks(runLoop);

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
}
