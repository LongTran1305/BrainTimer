package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestion = 0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;
    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct");
            score++;
        } else {
            resultTextView.setText("Wrong :)) ");
        }
        numberOfQuestion++;
        scoreTextView.setText(score + "/" + numberOfQuestion);

        newQuestion();
    }

    public void newQuestion() {
        Random random = new Random();
        int a = random.nextInt(25);
        int b = random.nextInt(25);
        sumTextView.setText(a + " + " + b);
        answer.clear();
        locationOfCorrectAnswer = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(51);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(51);
                }
                answer.add(wrongAnswer);
            }
        }
        btn0.setText(Integer.toString(answer.get(0)));
        btn1.setText(Integer.toString(answer.get(1)));
        btn2.setText(Integer.toString(answer.get(2)));
        btn3.setText(Integer.toString(answer.get(3)));
    }
    public void playAgain(View view){
        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("10s");
        scoreTextView.setText(score + "/" + numberOfQuestion);
        playAgainBtn.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        newQuestion();
        new CountDownTimer(10100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = findViewById(R.id.sumTxtView);
        resultTextView = findViewById(R.id.resultTxtView);
        scoreTextView = findViewById(R.id.scoreTxtView);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        timerTextView = findViewById(R.id.timerTxtView);
        playAgain(findViewById(R.id.playAgainBtn));

    }
}