package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

   Button goButton;
   ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextview;
    int score = 0;
    int NumberOfQuestions = 0 ;
    TextView ScoreTextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView questionTextview;
    TextView timerTextview;
    Button playAgainbutton;


    ConstraintLayout gameLayout;
   public void chooseAnswer(View view){
       TextView answerText = findViewById(R.id.answerTextView);
       if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
       {
           resultTextview.setText("Correct!");
            score ++;
       }
       else{
           resultTextview.setText("Wrong :/");
       }
       NumberOfQuestions++;
       ScoreTextview.setText(Integer.toString(score) + "/" + Integer.toString(NumberOfQuestions));
       newQuestion();

   }

    public void start(View View){
        goButton.setVisibility(android.view.View.INVISIBLE);
        playAgain(findViewById(R.id.button2));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion(){
        Random random=new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionTextview.setText(Integer.toString(a)+ " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else {
                int WrongAnswer = random.nextInt(41);
                while(WrongAnswer == a+b)
                {
                    WrongAnswer = random.nextInt(41);
                }
                answers.add(WrongAnswer);
            }
        }
        button0.setText(answers.get(0).toString());
        button1.setText(answers.get(1).toString());
        button2.setText(answers.get(2).toString());
        button3.setText(answers.get(3).toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.startButton);
         questionTextview = findViewById(R.id.QuestiontextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        timerTextview = findViewById(R.id.CountertextView);
        playAgainbutton= findViewById(R.id.playAgainbutton);

        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);

    gameLayout.setVisibility(View.INVISIBLE);

        resultTextview = findViewById(R.id.answerTextView);
        ScoreTextview = findViewById(R.id.ScoretextView);







    }


public void playAgain(View view){
        score = 0 ;
        NumberOfQuestions = 0;
        timerTextview.setText("30s");
    ScoreTextview.setText(Integer.toString(score) + "/" + Integer.toString(NumberOfQuestions));
        newQuestion();

        playAgainbutton.setVisibility(View.INVISIBLE );
    new CountDownTimer(30100, 1000){


        @Override
        public void onTick(long l) {
            timerTextview.setText(String.valueOf((l/1000) + "s"));
        }

        @Override
        public void onFinish() {
            resultTextview.setText("DONE!");
            playAgainbutton.setVisibility(View.VISIBLE);
        }
    }.start();

    }


}