package com.test.weis.yaniv.stopwatch1;

import android.widget.TextView;

import java.util.List;

public class Utils {
    private static final Utils ourInstance = new Utils();

    static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    /**
     * This methgetStringTime(lap);od sets the laps output text into the lapsListTextView TextView variable
     */
    public void printLaps(List<Long> lapsList, TextView lapsListOutput) {
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
    String getStringTime(long timePeriodMillis) {
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
