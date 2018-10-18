package com.omelchenkoaleks.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCountDownTimer mCountDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button startButton;
    private TextView text;
    private TextView timeElapsedView;

    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = this.findViewById(R.id.button);
        startButton.setOnClickListener(this);

        text = this.findViewById(R.id.timer);
        timeElapsedView = findViewById(R.id.timeElapsed);

        mCountDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View view) {
        if (!timerHasStarted) {
            mCountDownTimer.start();
            timerHasStarted = true;
            startButton.setText("Start");
        } else {
            mCountDownTimer.cancel();
            timerHasStarted = false;
            startButton.setText("RESET");
        }
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText("Time remain: " + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Time Elapsed: "
                    + String.valueOf(timeElapsed));
        }

        @Override
        public void onFinish() {
            text.setText("Time`s up!");
            timeElapsedView.setText("Time Elapsed: "
                    + String.valueOf(startTime));
        }
    }
}
