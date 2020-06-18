package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layoutRelative;

    TextView txtHigh;
    Button btnGo;
    CountDownTimer countDownTimer;
    TextView txtSum;
    TextView txtScore;
    TextView textView;
    TextView txtResult;
    Button btnPlayAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int attempt = 0;
    int HighScore = 0;

    boolean gamePlay = true;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;

    public void optionCreation() {


        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        answers.clear();

        txtSum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i != locationOfCorrectAnswer) {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);


            } else {

                answers.add(a + b);

            }

        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        if (gamePlay) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                txtResult.setText("Correct !");
                score++;
            } else {
                txtResult.setText("Incorrect !");
            }
            attempt++;
            txtScore.setText(Integer.toString(score) + "/" + Integer.toString(attempt));
            optionCreation();

        }

    }

    public void timer() {
        countDownTimer = new CountDownTimer(30100, 1000) {


            public void onTick(long millisecondsUtilDone) {

                btnPlayAgain = findViewById(R.id.btnPlayAgain);

                //Countdown is counting down
                //seekBar.setVisibility(View.INVISIBLE);

                String secondString = Long.toString(millisecondsUtilDone / 1000);
                if (millisecondsUtilDone / 1000 <= 9) {

                    secondString = "0" + secondString;

                }

                String timeLeft = secondString + "s";
                textView.setText(timeLeft);
                btnPlayAgain.setEnabled(false);
                btnPlayAgain.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {

                //Countdown finish

                if(score >= HighScore) {
                    HighScore = score;
                }
                txtResult.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(attempt));
                btnPlayAgain.setEnabled(true);
                btnPlayAgain.setVisibility(View.VISIBLE);
                gamePlay = false;
                txtHigh.setText("High Score: " + Integer.toString(HighScore));

            }
        }.start();
    }


    public void clicked (View view) {

        //textView.setText("30s");
        //btnPlayAgain.setVisibility(View.INVISIBLE);
        txtResult.setText("");
        txtScore.setText("0/0");
        score = attempt = 0;
        gamePlay = true;
        optionCreation();
        timer();

    }

    public void startGame(View view) {

        btnGo.setVisibility(View.INVISIBLE);
        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.VISIBLE);

        layoutRelative.setVisibility(View.VISIBLE);

        timer();
        clicked(findViewById(R.id.textView));
        optionCreation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutRelative = findViewById(R.id.layoutRelative);

        btnGo = findViewById(R.id.btnGo);
        textView = (TextView) findViewById(R.id.textView);
        txtScore = (TextView) findViewById(R.id.txtScore);
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtSum = (TextView) findViewById(R.id.txtSum);
        txtHigh = (TextView) findViewById(R.id.txtHigh);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btn0 = findViewById(R.id.btn);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

    }
}
