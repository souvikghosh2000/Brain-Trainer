package com.example.brainttrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {




    TextView gameOver;
    ConstraintLayout endLayout;
    ConstraintLayout gameLayout;
    Button restart;
    TextView timerTextView;
    TextView countdown;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    TextView scoreTextView;
    int locationOfCorrectAns;
    Button goButton;
    ArrayList<Integer> answers=new ArrayList<Integer>();     // this array is going to hold the options for the questions
    int score=0;                    //to keep track of score
    int numOfQues=0;                // to keep track of ques
    public void restart(View view)                              //for the function of restart button

    {

        score=0;
        numOfQues=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numOfQues));

        newQuestion();
        new CountDownTimer(10100,1000) {
            public void onTick(long millisecondsUntilDone) {
                timerTextView.setText(String.valueOf(millisecondsUntilDone/1000)+"s");
                restart.setVisibility(View.INVISIBLE);
                gameLayout.setVisibility(View.VISIBLE);
                endLayout.setVisibility(View.INVISIBLE);

            }

            public void onFinish() {

                restart.setVisibility(View.VISIBLE);
                gameLayout.setVisibility(View.INVISIBLE);
                endLayout.setVisibility(View.VISIBLE);
                gameOver.setVisibility(View.VISIBLE);
                restart.setVisibility(View.VISIBLE);
                countdown.setVisibility(View.INVISIBLE);



            }
        }.start();

    }
    public void chooseAnswer(View view)                            //this function indicates whether ans is correct or not
    {
        if(Integer.toString(locationOfCorrectAns).equals(view.getTag().toString()))       // it will help us to know what button the user has tapped
        {
            resultTextView.setText("Correct  :)");
            resultTextView.setVisibility(View.VISIBLE);

            score++;

        }
        else {
            resultTextView.setText("Wrong  :(");
            resultTextView.setVisibility(View.VISIBLE);
        }
        numOfQues++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numOfQues));
        newQuestion();

    }
    public void startButton(View view)                                             //function when go button is clicked
    {

        new CountDownTimer(3100,1000) {
            public void onTick(long millisecondsUntilDone) {
               countdown.setText(String.valueOf(millisecondsUntilDone/1000));

               gameLayout.setVisibility(View.INVISIBLE);

               endLayout.setVisibility(View.VISIBLE);
               gameOver.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);
               countdown.setVisibility(View.VISIBLE);

               goButton.setVisibility(View.INVISIBLE);



            }

            public void onFinish() {

                goButton.setVisibility(View.INVISIBLE);
                restart(findViewById(R.id.timertextView));
                gameLayout.setVisibility(View.VISIBLE);


            }
        }.start();

    }
    public void newQuestion()                                        //to generate new ques each time
    {

        Random random=new Random();
        int a=random.nextInt(21);    //lowest being 0 and highest being 20
        int b=random.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));       //display the random nnumbers on the textview
        locationOfCorrectAns=random.nextInt(4);                        //it will hold the position of the correct answer
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAns)
            {
                answers.add(a+b);           //add the correct answer into the arrayList
            }
            else
            {
                int wrongAnswer=random.nextInt(41);                 //cause the max sum between two numbers(20max and 20 max) is 40
                while(wrongAnswer==a+b)   {                                //if the wrong answer gets equal with the correct then change the wrong answer with a new value
                    wrongAnswer=random.nextInt(41);
                }
                answers.add(wrongAnswer);    //add the wrong answer into the arrayList


            }
        }
        button0.setText(Integer.toString(answers.get(0)));      //get the value of array position 0 and place it into button0
        button1.setText(Integer.toString(answers.get(1)));      //get the value of array position 1 and place it into button1
        button2.setText(Integer.toString(answers.get(2)));      //get the value of array position 2 and place it into button2
        button3.setText(Integer.toString(answers.get(3  )));      //get the value of array position 3 and place it into button3
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=(Button)findViewById(R.id.gobutton);
        countdown=findViewById(R.id.countdown);
        restart=(Button)findViewById(R.id.restart);
        sumTextView=(TextView)findViewById(R.id.questextView);
        gameOver=(TextView)findViewById(R.id.gameOver);
        button0 =(Button)findViewById(R.id.button0);
        button1 =(Button)findViewById(R.id.button1);
        button2 =(Button)findViewById(R.id.button2);
        button3 =(Button)findViewById(R.id.button3);
        resultTextView=findViewById(R.id.result);
        scoreTextView=findViewById(R.id.scoretextView);
        timerTextView=findViewById(R.id.timertextView);
        gameLayout=findViewById(R.id.mainlayout);
        endLayout=findViewById(R.id.endlayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        endLayout.setVisibility(View.INVISIBLE);








    }
}
