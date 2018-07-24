package com.example.monirul.learning;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class FrontPage extends AppCompatActivity implements View.OnClickListener{

    Button simpleQuestion;
    Button toughQuestion;
    Button otherApp;
    Button rateApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        simpleQuestion = findViewById(R.id.simple_questions);
        toughQuestion = findViewById(R.id.tough_questions);
        otherApp =   findViewById(R.id.other_app);
        rateApp = (Button)findViewById(R.id.rate_app);

        LinearLayout frontPage = findViewById(R.id.frontpage_titlebar_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

        simpleQuestion.setOnClickListener(this);
        toughQuestion.setOnClickListener(this);
        otherApp.setOnClickListener(this);
        rateApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.simple_questions:
                Intent i = new Intent(FrontPage.this,SimpleQuestion.class);
                startActivity(i);
                break;
            case R.id.tough_questions:
                Intent j = new Intent(FrontPage.this,ToughQuestions.class);
                startActivity(j);
                break;

            case R.id.other_app:

                break;

            case R.id.rate_app:

                break;

        }
    }
}
