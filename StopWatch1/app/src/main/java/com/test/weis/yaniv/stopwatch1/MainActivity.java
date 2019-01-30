package com.test.weis.yaniv.stopwatch1;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.PrintWriter;

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
        final Context context = getApplicationContext();
        String filesPath = context.getFilesDir().getAbsolutePath();

      // CREATING THE FIRST WATCH //////////////////////////
        final StopWatchInternal watch1 = createNewWatchBasedOn(R.id.tvTimer,
                R.id.btlist,
                R.id.btStart,
                R.id.btPause,
                R.id.btReset,
                R.id.btlap);


        // CREATING THE SECONF WATCH ///////////////////////////

        StopWatchInternal watch2 = createNewWatchBasedOn(R.id.tvTimer2,
                R.id.btlist2,
                R.id.btStart2,
                R.id.btPause2,
                R.id.btReset2,
                R.id.btlap2);

        initCsvButtonDFunctionalityFor(filesPath, watch1);
    }

    private StopWatchInternal createNewWatchBasedOn(int timerTextViewId,
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

        return createNewStopWatch(timerTextView, lapsListTextView, startButton, pauseButton, resetButton, lapButton);
    }

    private StopWatchInternal createNewStopWatch(TextView timerTextView, TextView lapsListTextView,
                                    Button start, Button pause, Button reset, Button lap) {
        return new StopWatchInternal(handler, start, pause, reset, lap, timerTextView, lapsListTextView);
    }


    private void initCsvButtonDFunctionalityFor(final String filesPath, final StopWatchInternal watch) {
        Button csvButton = findViewById(R.id.exportToCsv);

        csvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * open Device's File Explorer:
                 * Android Studio - View -> Tools Windows -> Device File Explorer
                 * the file will be under:
                 * /data/data/com.test.weis.yaniv.stopwatch1/files/stopwatch.csv
                 * you can then download it to your computer (Windows) and open in Excel :)
                 */
                String fileFullPath = filesPath + "/stopwatch.csv";
                File csvFile = new File(fileFullPath);

                try {
                    PrintWriter pw = new PrintWriter(csvFile);
                    StringBuilder sb = new StringBuilder();
                    sb.append("lap,");
                    sb.append("time\n");

                    int lapIndex = 1;
                    for (Long lapTime : watch.lapsList) {
                        sb.append(String.valueOf(lapIndex));
                        sb.append(',');
                        sb.append(String.valueOf(lapTime));
                        sb.append("\n");

                        lapIndex++;
                    }

                    pw.write(sb.toString());
                    pw.close();
                    System.out.println("done!");
                } catch (Exception e) {
                    //ignore
                }
            }
        });
    }
}