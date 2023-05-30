package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> numbers = new ArrayList<>();
    TextView result;
    int locateAnswer;
    int score = 0;
    int numberofquestions = 0;
    TextView scoreTextView;
    TextView sum;
    TextView timertextview;

    Button option0;
    Button option1;
    Button option2;
    Button option3;
    Button playagain;

    public void playagain(View view){
        score = 0;
        numberofquestions = 0;
        timertextview.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        playagain.setVisibility(View.INVISIBLE);
        result.setText("");
        reset();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                timertextview.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                result.setText("Game Over !!!");
                playagain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(Integer.toString(locateAnswer).equals(view.getTag())){
            result.setText("You got it right!!");
            score++;
        }else{
            result.setText("You got it wrong!!");
        }
        numberofquestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestions));
        reset();
    }

    public void reset(){

        Random random = new Random();
        int num1 = random.nextInt(21);
        int num2 = random.nextInt(21);
        sum.setText(Integer.toString(num1) + "+" + Integer.toString(num2));

        locateAnswer = random.nextInt(4);
        numbers.clear();
        for(int i=0;i<4;i++){
            if(i == locateAnswer){
                numbers.add(num1+num2);
            }else{
                int wronganswer = random.nextInt(41);
                while(wronganswer == num1+num2){
                    wronganswer = random.nextInt(41);
                }
                numbers.add(wronganswer);
            }
        }
        option0.setText(Integer.toString(numbers.get(0)));
        option1.setText(Integer.toString(numbers.get(1)));
        option2.setText(Integer.toString(numbers.get(2)));
        option3.setText(Integer.toString(numbers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        option0 = findViewById(R.id.button0);
        option1 = findViewById(R.id.button1);
        option2 = findViewById(R.id.button2);
        option3 = findViewById(R.id.button3);

        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result);
        scoreTextView = findViewById(R.id.questions);
        timertextview = findViewById(R.id.timer);
        playagain = findViewById(R.id.tryagain);
        playagain(findViewById(R.id.timer));

        playagain(timertextview = findViewById(R.id.timer));
    }
}