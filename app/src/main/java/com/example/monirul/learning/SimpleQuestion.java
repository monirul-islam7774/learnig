package com.example.monirul.learning;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monirul.learning.models.SimpleQuestionModel;

import java.util.ArrayList;
import java.util.Locale;

public class SimpleQuestion extends AppCompatActivity implements View.OnClickListener{
    TextView question,answer,currentNumber,totalNumber;
    Button prevButton,showButton,nextButton,startButton,stopButton;
    int index;

    ArrayList<SimpleQuestionModel> simpleQuestionList;

    //Variables and TextToSpeech object
    TextToSpeech textToSpeechObject;
    int result;
    int flag;

    private static final String default_answer = "Press the VIEW button to see the answer";

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        //Adding custom action bar
        LinearLayout questionPage = findViewById(R.id.question_title_bar_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.question_title_bar);

        TextView frontTextTitle = findViewById(R.id.question_title);
        frontTextTitle.setText("Simple Question");

        startButton = findViewById(R.id.speak_button);
        stopButton  = findViewById(R.id.mute_button);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);


        //Initializing Textview
        question = findViewById(R.id.questions);
        answer = findViewById(R.id.answers);
        currentNumber = findViewById(R.id.current_number);
        totalNumber = findViewById(R.id.total_number);


        //Initializing Button
        prevButton  = findViewById(R.id.prev_button);
        showButton  = findViewById(R.id.show_button);
        nextButton  = findViewById(R.id.next_button);


        this.initSimpleQuestionList();
        //Adding listener to buttons
        prevButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        //Setting up values to our variables and textviews
        index = 0;
        question.setText(simpleQuestionList.get(index).getQuestion());
        answer.setText(default_answer);
        currentNumber.setText(String.valueOf(index+1));
        totalNumber.setText("/"+simpleQuestionList.size());


        //Initialization of textToSpeech object and listener added
        textToSpeechObject = new TextToSpeech(SimpleQuestion.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i == TextToSpeech.SUCCESS){
                    result = textToSpeechObject.setLanguage(Locale.US);
                }
                else {

                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void initSimpleQuestionList() {
        this.simpleQuestionList = new ArrayList<>();
        String[] question = getResources().getStringArray(R.array.simple_ques);
        String[] answer   = getResources().getStringArray(R.array.simple_ans);
        //Importing the string arrays from strings.xml file
        for (int i = 0; i < question.length; i++){
            this.simpleQuestionList.add(new SimpleQuestionModel(question[i], answer[i], false));
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.prev_button:
                simpleQuestionList.get(index).setAnswerVisible(false);
                answer.setText(default_answer);
                showButton.setBackgroundResource(R.drawable.view);
                textToSpeechObject.stop();
                index--;
                if (index == -1){

                    index = simpleQuestionList.size()-1;
                    question.setText(simpleQuestionList.get(index).getQuestion());
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(simpleQuestionList.get(index).getQuestion());
                    currentNumber.setText(String.valueOf(index+1));
                }

                break;

            case R.id.show_button:
                if (!simpleQuestionList.get(index).isAnswerVisible()){
                    answer.setText(simpleQuestionList.get(index).getAnswer());
                    showButton.setBackgroundResource(R.drawable.hide);
                    simpleQuestionList.get(index).setAnswerVisible(true);
                }
                else{
                    simpleQuestionList.get(index).setAnswerVisible(false);
                    answer.setText(default_answer);
                    showButton.setBackgroundResource(R.drawable.view);
                    textToSpeechObject.stop();
                }


                break;

            case R.id.next_button:
                simpleQuestionList.get(index).setAnswerVisible(false);
                answer.setText(default_answer);
                showButton.setBackgroundResource(R.drawable.view);
                textToSpeechObject.stop();
                index++;
                if(index == simpleQuestionList.size()){
                    index = 0;
                    question.setText(simpleQuestionList.get(index).getQuestion());
                    currentNumber.setText(String.valueOf(index+1));
                }
                else {
                    question.setText(simpleQuestionList.get(index).getQuestion());
                    currentNumber.setText(String.valueOf(index+1));
                }


                break;

            case R.id.speak_button:
                if (result == TextToSpeech.LANG_NOT_SUPPORTED
                        || result == TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();

                }
                else if(answer.getText().toString().equals(simpleQuestionList.get(index).getAnswer())){

                    textToSpeechObject.speak(simpleQuestionList.get(index).getAnswer(),TextToSpeech.QUEUE_FLUSH,null);
                    flag=1;

                }
                else {

                    Toast.makeText(getApplicationContext(),"Please press the view button first",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.mute_button:

                if (textToSpeechObject != null){

                    textToSpeechObject.stop();
                    flag = 0;

                }
                break;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeechObject !=null){

            textToSpeechObject.stop();
            textToSpeechObject.shutdown();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (textToSpeechObject != null){

            textToSpeechObject.stop();


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (answer.getText().toString().equals(simpleQuestionList.get(index).getAnswer()) && flag == 1){

            textToSpeechObject.speak(simpleQuestionList.get(index).getAnswer(),TextToSpeech.QUEUE_FLUSH,null);
        }
    }

}
