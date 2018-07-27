package com.example.monirul.learning;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ToughQuestions extends AppCompatActivity implements View.OnClickListener{

    TextView question,answer,currentNumber,totalNumber;
    Button prevButton,showButton,nextButton,startButton,stopButton;
    int index;

    String[] toughQuestion;
    String[] toughAnswer;

    //Variables and TextToSpeech object
    TextToSpeech textToSpeechObject;
    int result;
    int flag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        //Adding custom action bar
        LinearLayout questionPage = findViewById(R.id.question_title_bar_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.question_title_bar);

        TextView frontTextTitle = findViewById(R.id.question_title);
        frontTextTitle.setText("Tough Question");

        startButton = findViewById(R.id.speak_button);
        stopButton = findViewById(R.id.mute_button);

        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        //Initializing Textview
        question = findViewById(R.id.questions);
        answer = findViewById(R.id.answers);
        currentNumber = findViewById(R.id.current_number);
        totalNumber = findViewById(R.id.total_number);

        //Initializing Button
        prevButton = findViewById(R.id.prev_button);
        showButton = findViewById(R.id.show_button);
        nextButton = findViewById(R.id.next_button);

        //Importing the string arrays from strings.xml file
        toughQuestion = getResources().getStringArray(R.array.tough_ques);
        toughAnswer = getResources().getStringArray(R.array.tough_ans);

        //Adding listener to buttons
        prevButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        //Setting up values to our variables and textviews
        index = 0;
        question.setText(toughQuestion[index]);
        answer.setText("Press the VIEW button to see the answer");
        currentNumber.setText(String.valueOf(index+1));
        totalNumber.setText("/"+String.valueOf(toughQuestion.length));

        textToSpeechObject = new TextToSpeech(ToughQuestions.this, new TextToSpeech.OnInitListener() {
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

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.prev_button:
                answer.setText("Press the VIEW button to see the answer");
                showButton.setBackgroundResource(R.drawable.view);
                textToSpeechObject.stop();
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
                    textToSpeechObject.stop();
                }
                break;

            case R.id.next_button:
                answer.setText("Press the VIEW button to see the answer");
                showButton.setBackgroundResource(R.drawable.view);
                textToSpeechObject.stop();
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

            case R.id.speak_button:
                if(result == TextToSpeech.LANG_NOT_SUPPORTED
                        || result == TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(getApplicationContext(),"Feature is not supported in your device",Toast.LENGTH_SHORT).show();

                }
                else if (answer.getText().toString().equals(toughAnswer[index])) {
                    textToSpeechObject.speak(toughAnswer[index],TextToSpeech.QUEUE_FLUSH,null);
                    flag = 1;
                }
                else {

                    Toast.makeText(getApplicationContext(),"Please press the view button first",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.mute_button:
                if (textToSpeechObject != null){

                    textToSpeechObject.stop();
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
        if (answer.getText().toString().equals(toughAnswer[index]) && flag == 1){

            textToSpeechObject.speak(toughAnswer[index],TextToSpeech.QUEUE_FLUSH,null);
            flag = 0;

        }
    }

}
