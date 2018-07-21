package com.example.monirul.learning;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleQuestion extends AppCompatActivity implements View.OnClickListener{
    TextView question,answer,currentNumber,totalNumber;
    Button prevButton,showButton,nextButton;
    int index;

    String[] simpleQuestion;
    String[] simpleAnswer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        question = findViewById(R.id.questions);
        answer = findViewById(R.id.answers);
        currentNumber = findViewById(R.id.current_number);
        totalNumber = findViewById(R.id.total_number);

        prevButton = findViewById(R.id.prev_button);
        showButton = findViewById(R.id.show_button);
        nextButton = findViewById(R.id.next_button);

        simpleQuestion = getResources().getStringArray(R.array.simple_ques);
        simpleAnswer = getResources().getStringArray(R.array.simple_ans);

        prevButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        index = 0;
        question.setText(simpleQuestion[index]);
        answer.setText("Press the VIEW button to see the answer");
        currentNumber.setText(String.valueOf(index+1));
        totalNumber.setText("/"+String.valueOf(simpleQuestion.length));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.prev_button:
                answer.setText("Press the VIEW button to see the answer");
                showButton.setBackgroundResource(R.drawable.view);
                index--;
                if (index == -1){

                    index = simpleAnswer.length-1;
                    question.setText(simpleQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(simpleQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }

                break;

            case R.id.show_button:
                if (answer.getText()== "Press the VIEW button to see the answer"){
                    answer.setText(simpleAnswer[index]);
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
                if(index == simpleQuestion.length){
                    index = 0;
                    question.setText(simpleQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(simpleQuestion[index]);
                    currentNumber.setText(String.valueOf(index+1));
                }


                break;

        }
    }
}
