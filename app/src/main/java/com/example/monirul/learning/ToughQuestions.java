package com.example.monirul.learning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToughQuestions extends AppCompatActivity implements View.OnClickListener{

    TextView question,answer,currentNumber,totalNumber;
    Button prevButton,showButton,nextButton;
    int index;

    String[] toughQuestion;
    String[] toughAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        toughQuestion = getResources().getStringArray(R.array.tough_ques);
        toughAnswer = getResources().getStringArray(R.array.tough_ans);

        question = findViewById(R.id.questions);
        answer = findViewById(R.id.answers);
        currentNumber = findViewById(R.id.current_number);
        totalNumber = findViewById(R.id.total_number);

        prevButton = findViewById(R.id.prev_button);
        showButton = findViewById(R.id.show_button);
        nextButton = findViewById(R.id.next_button);

        index = 0;
        question.setText(toughQuestion[index]);
        answer.setText("Press VIEW button to see the answer");
        currentNumber.setText(String.valueOf(index+1));
        totalNumber.setText("/"+String.valueOf(toughQuestion.length));

        prevButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.prev_button:
                answer.setText("Press the VIEW button to see the answer");
                showButton.setBackgroundResource(R.drawable.view);
                index--;
                if (index == -1){

                    index = toughAnswer.length-1;
                    question.setText(toughQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(toughQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }

                break;

            case R.id.show_button:
                if (answer.getText()== "Press the VIEW button to see the answer"){
                    answer.setText(toughAnswer[index]);
                    showButton.setBackgroundResource(R.drawable.hide);
                }
                else{
                    answer.setText("Press the VIEW button to see the answer");
                    showButton.setBackgroundResource(R.drawable.view);
                }
                break;

            case R.id.next_button:
                answer.setText("Press the VIEW button to see the answer");
                showButton.setBackgroundResource(R.drawable.view);
                index++;
                if (index == toughQuestion.length){

                    index = 0;
                    question.setText(toughQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(toughQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }
                break;
        }

    }
}
